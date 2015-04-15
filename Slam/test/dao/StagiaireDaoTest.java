package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class StagiaireDaoTest {

	@Test
	public void testFindSession() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificationStagiaires() {
		StagiaireDao stagiaireDao = new StagiaireDao();
		Boolean etatPremier = stagiaireDao.pVerificationStagiaires(1);
		Boolean etatSecond = stagiaireDao.pVerificationStagiaires(4);

		assertTrue(etatPremier);
		assertFalse(etatSecond);
	}
	
	@Test
	public void testFindSessionPersonne() {
		StagiaireDao stagiaireDao = new StagiaireDao();
		int idSession = stagiaireDao.findSessionPersonne(1);

		assertEquals(1, idSession);		
	}

}
