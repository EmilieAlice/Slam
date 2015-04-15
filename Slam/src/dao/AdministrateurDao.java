package dao;

import java.sql.ResultSet;

import modele.Personne;

public class AdministrateurDao {
	
	private static java.sql.PreparedStatement pFindAdminById = null;
	/**
	 * Requete pour récuperer l'admin
	 */
	static {
		try {
			pFindAdminById = DataBase.getConnection().prepareStatement(
					"SELECT * from administrateur where id_admin = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete pFindAdminById échouée.");
		}
	}

	/**
	 * Méthode qui récupère l'admin du site agriote
	 *
	 * @param idSession un entier
	 * @return collection de Module
	 */
	public Personne recupereAdmin(int idAdmin) {
		Personne admin = new Personne();
		try {
			pFindAdminById.setInt(1, idAdmin);
			ResultSet resultat = pFindAdminById.executeQuery();
			if (resultat.next()) {
				admin.setIdPersonne(resultat.getInt("id_admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
 
}
