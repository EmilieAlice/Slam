package dao;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import modele.Personne;

import org.junit.Test;

import dao.PersonneDAO;

public class PersonneDAOTest {
	
	/** Test de la methode valider() : On ecrit la personne attendu,
	 *  et on compare avec la personne que l'on va trouver grace à la methode
	 */	
	@Test
	public void testValider() throws SQLException {
		Timestamp time = Timestamp.valueOf("2014-12-20 16:36:31");
		Personne expected = new Personne(7, "Mr", "Pascal", "Waille", "12 rue d'en haut", "62270",
				"Rebreuviette", "03210337338", "0646808184", "pascal@hot.fr", "papi", time, true);
		PersonneDAO dao = new PersonneDAO();
		boolean result = dao.valider(7);
		assertEquals(expected, result);
	}
	
	/** Test de la methode findById() : On ecrit la personne attendu,
	 *  et on compare avec la personne que l'on va trouver grace à la methode
	 */
	@Test
	public void testFindById() throws SQLException {
		Timestamp time = Timestamp.valueOf("2014-12-20 16:36:31");
		Personne expected = new Personne(7, "Mr", "Pascal", "Waille", "12 rue d'en haut", "62270",
				"Rebreuviette", "03210337338", "0646808184", "pascal@hot.fr", "papi", time, true);
		PersonneDAO dao = new PersonneDAO();
		Personne result = dao.findById(7);
		assertEquals(expected, result);
		
	}
	
	/** Test de la methode insert() : On ecrit la nouvelle personne,
	 * et on l'insert dans la base de donnée grace à la methode
	 */
	@Test
	public void testInsert() throws SQLException {
		Date date= new Date();
		Timestamp time = new Timestamp(date.getTime());
		Personne expected = new Personne(7, "Mr", "Pascal", "Waille", "12 rue d'en haut", "62270",
				"Rebreuviette", "03210337338", "0646808184", "pascal@hot.fr", "papi", time, false);
		PersonneDAO dao = new PersonneDAO();
		dao.insert(expected);
	}
	
	/** Test de la methode update() : On ecrit la personne attendu, 
	 * et on la met a jour grace à la methode
	 */
	@Test
	public void testUpdate() throws SQLException {
		Timestamp time = Timestamp.valueOf("2014-12-17 11:29:18");
		Personne expected = new Personne(2, "Mle", "Emilie", "Waille", "25 avenue Gabriel", "92000", "Nanterre",
				"0170163146", "0646808184", "ewaille@hotmail.fr", "walle", time, false);
		PersonneDAO dao = new PersonneDAO();
		boolean result = dao.update(expected);
		assertEquals(expected, result);	
	}
	
}
