package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;

import modele.Personne;

import org.junit.Test;

import dao.PersonneDAO;

public class PersonneDAOTest {
	
	
	@Test
	public void testFindById() throws SQLException {
		Timestamp time = Timestamp.valueOf("2014-12-08 13:06:37");

		Personne expected = new Personne(2, "Mle", "Emilie", "WAILLE", "25 Avenue de la gare", "92000", "NANTERRE",
				"0956789101", "0321033738", "waille@hotmail.fr", "walle", time, false);
		PersonneDAO dao = new PersonneDAO();
		Personne result = dao.findById(2);
		assertEquals(expected, result);
	}


	@Test
	public void testEquals() {
		Timestamp time = Timestamp.valueOf("2014-12-08 13:06:37");
		Personne expected = new Personne(2, "Mle", "Emilie", "WAILLE", "25 Avenue de la gare", "92000", "NANTERRE",
				"0956789101", "0321033738", "waille@hotmail.fr", "walle", time, false);
		Personne result = new Personne(2, "Mle", "Emilie", "WAILLE", "25 Avenue de la gare", "92000", "NANTERRE",
				"0956789101", "0321033738", "waille@hotmail.fr", "walle", time, false);
		assertEquals(expected, result);
	}
}
