package dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class StagiaireHomeTest {

	@Test
	public void testFindSession() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificationStagiaires() {
		StagiaireHome stagiaireDao = new StagiaireHome();
		Boolean etatPremier = stagiaireDao.pVerificationStagiaires(1);
		Boolean etatSecond = stagiaireDao.pVerificationStagiaires(4);

		assertTrue(etatPremier);
		assertFalse(etatSecond);
	}
	
	@Test
	public void testFindSessionPersonne() {
		StagiaireHome stagiaireDao = new StagiaireHome();
		int idSession = stagiaireDao.findSessionPersonne(1);

		assertEquals(1, idSession);		
	}

}