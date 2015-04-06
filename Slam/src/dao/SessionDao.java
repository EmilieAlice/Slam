package dao;

import java.sql.ResultSet;

public class SessionDao {

	private static java.sql.PreparedStatement pFindNomSessionById = null;
	/**
	 * Requete pour récupérer une liste de modules
	 */
	static {
		try {
			pFindNomSessionById = DataBase.getConnection().prepareStatement(
					"SELECT nom from lagarenne2015.session "
							+ "where id_session=?");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSessionById échouée.");
		}
	}

	/**
	 * Méthode qui récupère le nom d'une session dans la base données
	 *
	 * @param idSession un entier
	 * @return collection de Module
	 */
	public String recupereleNomDeLaSession(int idSession) {
		String nomSession = new String();
		try {
			pFindNomSessionById.setInt(1, idSession);
			ResultSet resultat = pFindNomSessionById.executeQuery();
			if (resultat.next()) {
				nomSession = resultat.getString("nom_session");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nomSession;
	}
}
