package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluationHomeTest {

	@Test
	public void testFindSession() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMaxEvaluation() {
		EvaluationHome evaluationDao = new EvaluationHome();
		int nbreMaxEvaluation = evaluationDao.findMaxEvaluation(1);
		
		assertEquals(4, nbreMaxEvaluation);
	}

}
