package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class EvaluationDaoTest {
/*
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
	
	@Test
	public void testEnregistreMoyenne(){
		EvaluationDao evaluationDao = new EvaluationDao();
		Double moyenne = 11.0;
		
		Boolean etat = evaluationDao.enregistreMoyenne(1, moyenne);
		
		assertTrue(etat);
	}
	
	*/
	@Test
	public void testRecupereToutesMoyenne(){
		EvaluationDao evaluationDao = new EvaluationDao();
		ArrayList<Double> moyennesPrevue = new ArrayList<Double>();
		moyennesPrevue.add(11.0);
		moyennesPrevue.add(11.6);
		moyennesPrevue.add(8.3);
		moyennesPrevue.add(13.3);
		
		ArrayList<Double> moyennesBase = new ArrayList<Double>();
		moyennesBase = evaluationDao.recupereToutesMoyenne(5,1);
		
		for (int i = 0; i < moyennesPrevue.size(); i++) {
			assertEquals(moyennesPrevue.get(i), moyennesBase.get(i));
			
		}
	}
	
}
