package dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class EvaluationHomeTest {

	@Test
	public void testFindMaxEvaluation() {
		EvaluationDao evaluationDao = new EvaluationDao();
		int nbreMaxEvaluation = evaluationDao.findMaxEvaluation(1);

		assertEquals(7, nbreMaxEvaluation);
	}

	@Test
	public void testInsertEvaluation() {
		EvaluationDao evaluationDao = new EvaluationDao();
		Boolean etat = evaluationDao.insertEvaluation(1, 1, 4);

		assertTrue(etat);

	}

}
