package dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import modele.Personne;

public class PersonneHome implements Dao<Personne>  {
	
	private static PreparedStatement pValider = null;
	static{
		try{
			pValider = DataBase.getConnection().prepareStatement("update personne set est_inscrite = true where email = ? date_inscription = ?"
				+ " and date_add(date_inscription, interval 1 minute) > now()");
		}
		catch(SQLException e){
		}
	}
	

	/** Valider l'inscription. Renvoie true si c'est ok, false sinon (délai expiré
	 * ou personne pas trouvée)
	 * @param id de l'utilisateur dont on souhaite valider l'inscription
	 * @return true or false
	 * @throws SQLException
	 */
	public boolean valider(String email, Timestamp date) throws SQLException {
		pValider.setString(1, email);
		pValider.setTimestamp(2, date);
		int nbAffectes = pValider.executeUpdate();
		return nbAffectes == 1;
	}
	
	private static PreparedStatement pFindById = null;
	static{
		try{
			pFindById = DataBase.getConnection().prepareStatement("select * from personne where id_personne = ? ;");
		}
		catch(SQLException e){
		}
	}
	
	/** Renvoie la personne de l'id donnée, ou rien si elle n'existe pas 
	 * @param id de la personne que l'on souhaite trouver
	 * @return la personne correspondante
	 * @throws SQLException
	 */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		pFindById.setInt(1, id);
		ResultSet res = pFindById.executeQuery();
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
	
	private static PreparedStatement pInsert = null;
	static{
		try{
			pInsert = DataBase.getConnection().prepareStatement("INSERT INTO personne (civilite, prenom, nom,"
					+ "adresse, code_postal, ville, telephone, telephone2,"
					+ "email, mot_passe, date_inscription, est_inscrite) VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		}
		catch(SQLException e){
		}
	}
	
	/** Creation d'une nouvelle personne dans la table personne de la base de donnée.
	 * @param la nouvelle personne à creer
	 * @throws SQLException
	 */
	@Override
	public void insert(Personne personne) throws SQLException {
		pInsert.setString(1, personne.getCivilite());
		pInsert.setString(2, personne.getPrenom());
		pInsert.setString(3, personne.getNom());
		pInsert.setString(4, personne.getAdresse());
		pInsert.setString(5, personne.getCodePostal());
		pInsert.setString(6, personne.getVille());
		pInsert.setString(7, personne.getTelephone());
		pInsert.setString(8,personne.getTelephone2());
		pInsert.setString(9, personne.getEmail());
		pInsert.setString(10, personne.getMotPasse());
		pInsert.setTimestamp(11, personne.getDateInscription());
		pInsert.setBoolean(12, personne.isEstInscrite());
		pInsert.executeUpdate();
	}
	
	private static PreparedStatement pUpdate = null;
	static{
		try{
			pUpdate = DataBase.getConnection().prepareStatement("UPDATE personne SET `id_personne` = ?, "
					+ "`civilite` = ?, `prenom` = ?, `nom` = ?, "
					+ "`adresse` = ?, `code_postal` = ?, `ville` = ?, "
					+ "`telephone` = ?, `telephone2` = ?, `email` = ?, "
					+ "`mot_passe` = ?, `date_inscription` = ?, `est_inscrite` = ? "
					+ "WHERE `id_personne` = ? ");
		}
		catch(SQLException e){
		}
	}

	/** Met à jour les données d'une personne dans la table personne de la base de donnée.
	 * @param la personne que l'on souhaite mettre à jour
	 * @return true or false
	 * @throws SQLException
	 */
	@Override
	public boolean update(Personne personne) throws SQLException{
		pUpdate.setInt(1, personne.getIdPersonne());
		pUpdate.setString(2, personne.getCivilite());
		pUpdate.setString(3, personne.getPrenom());
		pUpdate.setString(4, personne.getNom());
		pUpdate.setString(5, personne.getAdresse());
		pUpdate.setString(6, personne.getCodePostal());
		pUpdate.setString(7, personne.getVille());
		pUpdate.setString(8, personne.getTelephone());
		pUpdate.setString(9, personne.getTelephone2());
		pUpdate.setString(10, personne.getEmail());
		pUpdate.setString(11, personne.getMotPasse());
		pUpdate.setTimestamp(12, personne.getDateInscription());
		pUpdate.setBoolean(13, personne.isEstInscrite());
		pUpdate.setInt(14, personne.getIdPersonne());
		pUpdate.execute();
		return true;
	}

	/** Supprime une personne de la table personne dans la base de donnée, en donnant son id.
	 * @param id de la personne que l'on souhaite supprimer
	 * @return true or false
	 * @throws SQLException
	 */
	@Override
	public boolean delete(int id) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
	}


}
