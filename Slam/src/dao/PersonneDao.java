package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import modele.Personne;

public class PersonneDao implements Dao<Personne> {

	//--------------------------------------------------------------------------------------------------
	// INSERT
	//--------------------------------------------------------------------------------------------------

	private static String sqlInsert = "INSERT INTO personne (civilite, prenom, nom,"
			+ "adresse, code_postal, ville, telephone, telephone2,"
			+ "email, mot_passe, date_inscription, est_inscrite) VALUES"
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	/**
	 * Insère la personne dans la table personne de la base de donnée.
	 * Positionne <code>id</code> et <code>dateInscription</code> dans
	 * <code>personne</code>, de même que la procédure le fait dans la base :
	 * base et bean sont ainsi synchrones. <br/>
	 * Requiert personne != null. <br/>
	 * Utilise une transaction pour insérer et lire la date générée.
	 *
	 * @param la nouvelle personne à creer
	 * @throws SQLException
	 */
	@Override
	public void insert(Personne personne) throws SQLException {
		assert personne != null;
		Connection connection = DataBase.getConnection();
		// Commencer une transaction
		connection.setAutoCommit(false);
		PreparedStatement pInsert = null;
		try {
			pInsert = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			pInsert.setString(1, personne.getCivilite());
			pInsert.setString(2, personne.getPrenom());
			pInsert.setString(3, personne.getNom());
			pInsert.setString(4, personne.getAdresse());
			pInsert.setString(5, personne.getCodePostal());
			pInsert.setString(6, personne.getVille());
			pInsert.setString(7, personne.getTelephone());
			pInsert.setString(8, personne.getTelephone2());
			pInsert.setString(9, personne.getEmail());
			pInsert.setString(10, personne.getMotPasse());
			pInsert.setTimestamp(11, personne.getDateInscription());
			pInsert.setBoolean(12, personne.isEstInscrite());
			pInsert.executeUpdate();
			// Recuperer le id, mis a jour par la BD
			ResultSet rs = pInsert.getGeneratedKeys();
			if (rs.next()) {
				personne.setIdPersonne(rs.getInt(1));
				System.out.println("id : " + personne.getIdPersonne());
			}
			// Recuperer la date d'inscription, mise a jour par la BD
			// Il faut refaire une requete sql en SELECT
			String sql = "SELECT date_inscription FROM personne WHERE id_personne="
					+ personne.getIdPersonne();
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next(); // La personne est dans la BD
			personne.setDateInscription(rs.getTimestamp("date_inscription"));
			// Valider la transaction
			connection.commit();
		} catch (SQLException exc) {
			// Annuler toutes les opérations de la transaction
			connection.rollback();
			// Propager l'exception car la methode echoue
			throw exc;
		} finally {
			pInsert.close();
			connection.close();
		}
	}
	//--------------------------------------------------------------------------------------------------
	// INSERT VIA PROCEDUDE SQL
	// GROSSE ERREUR SUR LES 2 INSERT, QUI L'A CREE?
	//--------------------------------------------------------------------------------------------------


	/**
	 * Insère la personne via la procédure stockée <code>inserer_personne</code>
	 * . Positionne <code>id</code> et <code>dateInscription</code> dans
	 * <code>personne</code>, de même que la procédure le fait dans la base :
	 * base et bean sont ainsi synchrones. <br/>
	 * Requiert personne != null
	 *//*
	public static void insertViaProcedureMySQL(Personne personne)
			throws SQLException {
		assert personne != null;
		Connection connection = DataBase.getConnection();
		String sql = "CALL inserer_personne(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		CallableStatement cs = connection.prepareCall(sql);
		cs.setString(2, personne.getCivilite());
		cs.setString(3, personne.getPrenom());
		cs.setString(4, personne.getNom());
		cs.setString(5, personne.getAdresse());
		cs.setString(6, personne.getCodePostal());
		cs.setString(7, personne.getVille());
		cs.setString(8, personne.getTelephone());
		cs.setString(9, personne.getTelephone2());
		cs.setString(10, personne.getEmail());
		cs.setString(11, personne.getMotPasse());
		cs.execute();
		int id = cs.getInt(1);
		Timestamp date = cs.getTimestamp(12);
		personne.setIdPersonne(id);
		personne.setDateInscription(date);
	}*/
	//--------------------------------------------------------------------------------------------------
	// DELETE
	//--------------------------------------------------------------------------------------------------

	private static java.sql.PreparedStatement sqlDelete = null;
	/**
	 * Requete qui supprime une personne grace à son id
	 */
	static {
		try {
			sqlDelete = DataBase.getConnection().prepareStatement(
					"DELETE FROM personne WHERE id_personne = ?");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete sqlDelete échouée.");
		}
	}
	/**
	 * Supprime une personne de la table personne dans la base de donnée, en
	 * donnant son id.
	 *
	 * @param id
	 *            de la personne que l'on souhaite supprimer
	 * @return true or false
	 * @throws SQLException
	 */
	@Override
	public boolean delete(int id) throws SQLException {
		sqlDelete.setInt(1, id);
		sqlDelete.executeQuery();
		sqlDelete.close();
		return true;
	}

	//--------------------------------------------------------------------------------------------------
	// VALIDER
	//--------------------------------------------------------------------------------------------------


	/** Temps maximum de validité du lien pour confirmer son inscription */
	private final static String DELAI_VALIDATION = "1 hour";
	private static java.sql.PreparedStatement pValiderInscription = null;
	/**
	 * Requete mettre à jour l'etat d'inscription d'une personne
	 */
	static {
		try {
			pValiderInscription = DataBase.getConnection().prepareStatement(
					"UPDATE personne SET est_inscrite = true WHERE email = ? "
							+ "AND date_inscription = ? "
							+ "AND date_add(date_inscription, interval " + DELAI_VALIDATION + ") "
							+ "> NOW();");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete sqlValider échouée.");
		}
	}
	/**
	 * Valider l'inscription. Renvoie true si c'est ok, false sinon (délai
	 * expiré ou personne pas trouvée)
	 *
	 * @param id de l'utilisateur dont on souhaite valider l'inscription
	 * @return true or false
	 * @throws SQLException
	 */
	public boolean validerInscription(String email, Timestamp date) throws SQLException {
		pValiderInscription.setString(1, email);
		pValiderInscription.setTimestamp(2, date);
		int nbAffectes = pValiderInscription.executeUpdate();
		return nbAffectes == 1;
	}

	//--------------------------------------------------------------------------------------------------
	// UPDATE
	//--------------------------------------------------------------------------------------------------


	private static java.sql.PreparedStatement sqlUpdate = null;
	/**
	 * Requete qui permet de mettre à jour une personne
	 */
	static {
		try {
			sqlUpdate = DataBase.getConnection().prepareStatement(
					"UPDATE personne SET civilite = ?,"
							+ " prenom = ?, nom = ?, adresse = ?, code_postal = ?,"
							+ " ville = ?, telephone = ?, telephone2 = ?, email = ?,"
							+ " mot_passe = ?, date_inscription = ?, est_inscrite = ?"
							+ " WHERE id_personne = ? ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete sqlUpdate échouée.");
		}
	}

	/**
	 * Met à jour les données d'une personne dans la table personne de la base
	 * de donnée.
	 *
	 * @param la personne que l'on souhaite mettre à jour
	 * @return true or false
	 * @throws SQLException
	 */
	@Override
	public boolean update(Personne personne) throws SQLException {
		sqlUpdate.setString(1, personne.getCivilite());
		sqlUpdate.setString(2, personne.getPrenom());
		sqlUpdate.setString(3, personne.getNom());
		sqlUpdate.setString(4, personne.getAdresse());
		sqlUpdate.setString(5, personne.getCodePostal());
		sqlUpdate.setString(6, personne.getVille());
		sqlUpdate.setString(7, personne.getTelephone());
		sqlUpdate.setString(8, personne.getTelephone2());
		sqlUpdate.setString(9, personne.getEmail());
		sqlUpdate.setString(10, personne.getMotPasse());
		sqlUpdate.setTimestamp(11, personne.getDateInscription());
		sqlUpdate.setBoolean(12, personne.isEstInscrite());
		sqlUpdate.setInt(13, personne.getIdPersonne());
		sqlUpdate.execute();
		sqlUpdate.close();
		return true;
	}

	//--------------------------------------------------------------------------------------------------
	// FINDBYID
	//--------------------------------------------------------------------------------------------------

	private static java.sql.PreparedStatement sqlFindById = null;
	/**
	 * Requete qui selectionne tous les champs d'une personne grace à son id
	 */
	static {
		try {
			sqlFindById = DataBase.getConnection().prepareStatement(
					"select * from personne where id_personne = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete sqlFindById échouée.");
		}
	}
	/**
	 * Renvoie la personne de l'id donnée, ou rien si elle n'existe pas
	 *
	 * @param id de la personne que l'on souhaite trouver
	 * @return la personne correspondante
	 * @throws SQLException
	 */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		sqlFindById.setInt(1, id);
		ResultSet res = sqlFindById.executeQuery();
		if (res.next()) {
			result = new Personne(res.getInt("id_personne"),
					res.getString("civilite"), res.getString("prenom"),
					res.getString("nom"), res.getString("adresse"),
					res.getString("code_postal"), res.getString("ville"),
					res.getString("telephone"), res.getString("telephone2"),
					res.getString("email"), res.getString("mot_passe"),
					res.getTimestamp("date_inscription"),
					res.getBoolean("est_inscrite"));
		}
		res.close();
		sqlFindById.close();
		return result;
	}



	//--------------------------------------------------------------------------------------------------
	// CHECK MAIL
	// LES COMMENTAIRES D'EXPLICATIONS POUR CELUI QUI A CREE CET METHODE MERCI !
	//--------------------------------------------------------------------------------------------------


	private static String sqlCheckMail = "SELECT email FROM personne WHERE email = ?";

	public boolean checkEmail(String email) throws SQLException {
		boolean verif = false;
		Connection connection = DataBase.getConnection();
		PreparedStatement pCheckMail = connection
				.prepareStatement(sqlCheckMail);
		pCheckMail.setString(1, email);
		ResultSet result = pCheckMail.executeQuery();
		if (result.next()) {
			verif = true;
		}
		pCheckMail.close();
		connection.close();
		return verif;
	}

	//--------------------------------------------------------------------------------------------------
	// FIND PERSONNE BY NOM MODULE
	//--------------------------------------------------------------------------------------------------

	private static java.sql.PreparedStatement pFindPersonneByNomModule = null;
	/**
	 * Requete pour récupérer une personne grâce à son nom de module
	 */
	static {
		try {
			pFindPersonneByNomModule = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.formateur "
									+ "INNER JOIN personne on personne.id_personne = formateur.id_formateur "
									+ "INNER JOIN module on module.id_module = formateur.id_module "
									+ "WHERE module.nom = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findPersonneByNomModule échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur
	 * 
	 * @param nomModule
	 * @return personne
	 */
	public Personne findPersonneByNomModule(String nomModule) {
		Personne personne = new Personne();
		try {
			pFindPersonneByNomModule.setString(1, nomModule);
			ResultSet resultat = pFindPersonneByNomModule.executeQuery();
			if (resultat.next()) {
				personne.setIdPersonne(resultat.getInt("id_personne"));
				personne.setCivilite(resultat.getString("civilite"));
				personne.setPrenom(resultat.getString("prenom"));
				personne.setNom(resultat.getString("nom"));
				personne.setAdresse(resultat.getString("adresse"));
				personne.setCodePostal(resultat.getString("code_postal"));
				personne.setVille(resultat.getString("ville"));
				personne.setTelephone(resultat.getString("telephone"));
				personne.setTelephone2(resultat.getString("telephone"));
				personne.setEmail(resultat.getString("email"));
			} else {
				personne = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personne;
	}

}
