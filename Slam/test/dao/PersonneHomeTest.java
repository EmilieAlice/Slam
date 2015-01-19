package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import modele.Personne;
import org.junit.Test;

public class PersonneHomeTest extends AgriotesTestCase {

	/**
	 * Test de la methode valider() : On ecrit la personne attendu, et on
	 * compare avec la personne que l'on va trouver grace à la methode
	 */
	@Test
	public void testValider() throws SQLException {
		Date now = new Date();
		Timestamp time = new Timestamp(now.getTime());
		Personne personne = new Personne(7, "Mr", "Pascal", "Waille",
				"12 rue d'en haut", "62270", "Rebreuviette", "03210337338",
				"0646808184", "pascal@hot.fr", "papi", time, false);
		PersonneHome dao = new PersonneHome();
		dao.insert(personne);
		boolean ok = dao.valider("pascal@hot.fr", time);
		assertTrue(ok);
		Personne result = dao.findById(7);
		assertTrue(result.isEstInscrite());
		assertTrue(personne.isEstInscrite());
	}

	/**
	 * Test de la methode findById() : On ecrit la personne attendu, et on
	 * compare avec la personne que l'on va trouver grace à la methode
	 */
	@Test
	public void testFindById() throws SQLException {
		Timestamp time = Timestamp.valueOf("2014-12-17 11:29:18");
		Personne expected = new Personne(2, "Mle", "Emilie", "WAILLE",
				"25 Avenue de la gare", "92000", "NANTERRE", "0956789101",
				null, "waille@hotmail.fr", "walle", time, false);
		PersonneHome dao = new PersonneHome();
		Personne result = dao.findById(2);
		System.out.println("e: " + expected);
		System.out.println("r: " + result);
		assertEquals(expected, result);

	}

	/**
	 * Test de la methode insert() : On ecrit la nouvelle personne, et on
	 * l'insert dans la base de donnée grace à la methode
	 */
	@Test
	public void testInsert() throws SQLException {
		System.out.println("insert");
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		// Suprimer la precision en-dessous des secondes,
		// pas prises en compte dans le Timestamp de MySQL
		time.setNanos(0);
		Personne personne = new Personne(0, "Mr", "Pascal", "Waille",
				"12 rue d'en haut", "62270", "Rebreuviette", "03210337338",
				"0646808184", "pascal@hot.fr", "papi", time, false);
		PersonneHome dao = new PersonneHome();
		dao.insert(personne);
		personne.setIdPersonne(7);
		Personne result = dao.findById(7);
		System.out.println("e: " + personne);
		System.out.println("r: " + result);
		assertEquals(personne, result);
	}
}
