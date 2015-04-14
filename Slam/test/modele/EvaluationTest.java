package modele;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dao.EvaluationDao;

public class EvaluationTest {

	@Test
	public void testCalculMoyenne() {
		Evaluation evaluation = new Evaluation();
		ArrayList<Double> listeNotesBase = new ArrayList<Double>();
		listeNotesBase.add(13.0);
		listeNotesBase.add(14.0);
		listeNotesBase.add(15.0);
		evaluation.setListeDesNotes(listeNotesBase);
		
		Double expected = new Double(14.0);
		Double calcule = evaluation.calculMoyenne();
		
		assertEquals(expected, calcule);
	}

	@Test
	public void testCalculNoteLaPlusHaute() {
		Evaluation evaluation = new Evaluation();
		ArrayList<Double> listeNotesBase = new ArrayList<Double>();
		listeNotesBase.add(13.0);
		listeNotesBase.add(14.0);
		listeNotesBase.add(15.0);
		evaluation.setListeDesNotes(listeNotesBase);
		
		Double expected = new Double(15.0);
		Double calcule = evaluation.calculNoteLaPlusHaute();
		
		assertEquals(expected, calcule);
	}

	@Test
	public void testCalculNoteLaPlusBasse() {
		Evaluation evaluation = new Evaluation();
		ArrayList<Double> listeNotesBase = new ArrayList<Double>();
		listeNotesBase.add(13.0);
		listeNotesBase.add(14.0);
		listeNotesBase.add(15.0);
		evaluation.setListeDesNotes(listeNotesBase);
		
		Double expected = new Double(13.0);
		Double calcule = evaluation.calculNoteLaPlusBasse();
		
		assertEquals(expected, calcule);
	}
	
	@Test
	public void testFindLesNotes() {
		Evaluation evaluation = new Evaluation();
		EvaluationDao evaluationDao = new EvaluationDao();
		evaluation.setListeDesNotes(evaluationDao.findListeNotes(1));
		ArrayList<Double> listeNotesBase = new ArrayList<Double>();
		
		listeNotesBase.add(14.0);
		listeNotesBase.add(12.0);
		listeNotesBase.add(12.0);
		
		for (int i=0; i<listeNotesBase.size(); i++) {
			assertEquals(listeNotesBase.get(i), evaluation.getListeDesNotes().get(i));
		}
		
		

	}

}
