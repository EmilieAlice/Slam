package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Renvoie des maps pour peupler des listes déroulantes.
 * @author jamal
 *
 */
public class MapDao {
	/**
	 * Liste des sessions (id et nom)
	 * @return
	 */
	public static Map<String, String> getSessions() throws SQLException {
		HashMap<String, String> result = new HashMap<String, String>();
		String sql = "SELECT id_session, nom FROM session";
		Connection connexion = DataBase.getConnection();
		Statement smt = connexion.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()){
			result.put(rs.getInt("id_session") + "", rs.getString("nom"));
		}
		return result;
	}
}
