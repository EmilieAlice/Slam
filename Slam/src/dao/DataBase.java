package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBase {

	/** Code erreur MySQL pour serveur pas dispo */
	public static final int SERVER_OFF = 0;
	/** Code erreur MySQL pour doublon (duplicate entry for key ...) */
	public static final int DOUBLON = 1062;
	/** Code erreur MySQL quand on essaie de supprimer une ligne referencee par une autre */
	public static final int ROW_IS_REFERENCED = 1451;
	/** Code erreur MySQL pour parent row not found */
	public static final int FOREIGN_KEY_NOT_FOUND = 1452;

	
	protected static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	protected static final String URL = "jdbc:mysql://localhost:3306/lagarenne2015";
	//protected static final String sURL = "jdbc:mysql://192.168.92.10/lagarenne2015";
	protected static final String USER = "lagarenne2015";
	protected static final String PASSWORD = "lagarenne2015";

	public enum SortOrder {
		ASC, DESC;
	}

	static {
		// Chargement du pilote
		// Ne doit avoir lieu qu'une seule fois
		try {
			Class.forName(DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded.");
		}
		catch (ClassNotFoundException e) {
			System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
		}
		catch (InstantiationException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
			System.err.println(e.getMessage());
		}
		catch (IllegalAccessException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
			System.err.println(e.getMessage());
		}
	}

	/** Fournit une connexion à la base de données.
	 * Ne fait pas appel à un pool de connexion, même si cela est envisageable plus tard
	 * (ne changerait rien à l'appel de la méthode)
	 * @throws java.sql.SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}


