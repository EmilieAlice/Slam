package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import modele.Personne;

public class PersonneDAO implements Dao<Personne>  {

	/** Valider l'inscription. Renvoie true si c'est ok, false sinon (délai expiré
	 * ou personne pas trouvée)
	 * @param int id
	 * @return nbAffectes
	 * @throws SQLException
	 */
	public boolean valider(int id) throws SQLException {
		String req = "update personne set est_inscrite = true where id_personne = ?"
				+ " and date_add(date_inscription, interval 1 minute) > now()";
		Connection db = DataBase.getConnection();
		PreparedStatement psValider = db.prepareStatement(req);
		psValider.setInt(1, id);
		int nbAffectes = psValider.executeUpdate();
		return nbAffectes == 1;
	}

	
	/** Renvoie la personne de id donné, ou null si pas trouvé 
	 * @param int id
	 * @return resultat
	 * @throws SQLException
	 */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		String req = "select * from personne where id_personne = ? ;";
		Connection db = DataBase.getConnection();
		PreparedStatement psFindById = db.prepareStatement(req);
		psFindById.setInt(1, id);
		ResultSet res = psFindById.executeQuery();
		if (res.next()){
			result = new Personne(res.getInt("id_personne"), res.getString("civilite"), res.getString("prenom"),
					res.getString("nom"), res.getString("adresse"), res.getString("code_postal"),
					res.getString("ville"), res.getString("telephone"), res.getString("telephone2"),
					res.getString("email"), res.getString("mot_passe"), res.getTimestamp("date_inscription"),
					res.getBoolean("est_inscrite")
					);
		}
		return result;
	}
	/** Creation d'une nouvelle personne dans la table personne de la base de donnée.
	 * @param Personne personne
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void insert(Personne personne) throws SQLException {
		String req = "INSERT INTO personne (id_personne, civilite, prenom, nom,"
				+ "adresse, code_postal, ville, telephone, telephone2,"
				+ "email, mot_passe, date_inscription, est_inscrite) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection db = DataBase.getConnection();
		PreparedStatement psInsert = db.prepareStatement(req);
		psInsert.setInt(1, personne.getIdPersonne());
		psInsert.setString(2, personne.getCivilite());
		psInsert.setString(3, personne.getPrenom());
		psInsert.setString(4, personne.getNom());
		psInsert.setString(5, personne.getAdresse());
		psInsert.setString(6, personne.getCodePostal());
		psInsert.setString(7, personne.getVille());
		psInsert.setString(8, personne.getTelephone());
		psInsert.setString(9,personne.getTelephone2());
		psInsert.setString(10, personne.getEmail());
		psInsert.setString(11, personne.getMotPasse());
		psInsert.setTimestamp(12, personne.getDateInscription());
		psInsert.setBoolean(13, personne.isEstInscrite());
		psInsert.executeUpdate();
	}
	/** Met à jour les données d'une personne dans la table personne de la base de donnée.
	 * @param int id
	 * @return true
	 * @throws SQLException
	 */
	@Override
	public boolean update(Personne personne) throws SQLException {
		String req = "UPDATE personne SET `id_personne` = ?, "
				+ "`civilite` = ?, `prenom` = ?, `nom` = ?, "
				+ "`adresse` = ?, `code_postal` = ?, `ville` = ?, "
				+ "`telephone` = ?, `telephone2` = ?, `email` = ?, "
				+ "`mot_passe` = ?, `date_inscription` = ?, `est_inscrite` = ? "
				+ "WHERE `id_personne` = ? ";
		Connection db = DataBase.getConnection();
		PreparedStatement psUpdate = db.prepareStatement(req);
		psUpdate.setInt(1, personne.getIdPersonne());
		psUpdate.setString(2, personne.getCivilite());
		psUpdate.setString(3, personne.getPrenom());
		psUpdate.setString(4, personne.getNom());
		psUpdate.setString(5, personne.getAdresse());
		psUpdate.setString(6, personne.getCodePostal());
		psUpdate.setString(7, personne.getVille());
		psUpdate.setString(8, personne.getTelephone());
		psUpdate.setString(9, personne.getTelephone2());
		psUpdate.setString(10, personne.getEmail());
		psUpdate.setString(11, personne.getMotPasse());
		psUpdate.setTimestamp(12, personne.getDateInscription());
		psUpdate.setBoolean(13, personne.isEstInscrite());
		psUpdate.setInt(14, personne.getIdPersonne());
		psUpdate.execute();
		return true;
	}

	/** Supprime une personne de la table personne dans la base de donnée, en donnant son id.
	 * @param int id
	 * @return 
	 * @throws SQLException
	 */
	@Override
	public boolean delete(int id) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
	}


}
