package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import modele.Session;

public class SessionDao {

	private static java.sql.PreparedStatement pFindNomSessionById = null;
	/**
	 * Requete pour récupérer une liste de modules
	 */
	static {
		try {
			pFindNomSessionById = DataBase.getConnection().prepareStatement(
					"SELECT nom_session from lagarenne2015.session "
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
	
	private static String sqlFindAllSessions = "select * from session";
	
	public List<Session> retrieveAll() throws SQLException {
		PreparedStatement prepareStatement = DataBase.getConnection().prepareStatement(sqlFindAllSessions);
		ResultSet rs = prepareStatement.executeQuery();
		
		List<Session> results = new ArrayList<>();
		while(rs.next()) {
			Session session = new Session(rs.getInt("id_session"), rs.getString("nom"), rs.getString("description"));
			results.add(session);
		}
		
		return results;
	}
}
