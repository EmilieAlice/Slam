package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NoteDaoTest {

	@Test
	public void testInsertNote() {
		
		NoteDao noteDao = new NoteDao();
		Boolean test = noteDao.insertNote(1, 2, 12.0);
		
		assertTrue("Le test du Insert doit etre vrai si l'insert s'est bien passé", test);
	}
	
	@Test
	public void testRecupereNoteParModule() {
		ArrayList<Double> listeDeNotesPrevue = new ArrayList<Double>();
		listeDeNotesPrevue.add(14.0);
		listeDeNotesPrevue.add(10.0);
		
		ArrayList<Double> listeDeNotes = new ArrayList<Double>();
		NoteDao noteDao = new NoteDao();
		listeDeNotes = noteDao.recupereNote(3, 1);
		
		int i = 0;
		while(i<listeDeNotes.size()){
			assertEquals(listeDeNotesPrevue.get(i), listeDeNotes.get(i));
			System.out.println("Pévue : " + listeDeNotesPrevue.get(i) + " récupéreé : " + listeDeNotes.get(i));
			i++;
		}

	}
	
	@Test
	public void testrecupereNoteStagaire(){
		Double note = new Double(0);
		NoteDao noteDao = new NoteDao();
		note = noteDao.recupereNoteStagaire(1, 1);
		
		Double expected = new Double(14.0);
		
		System.out.println(note);
		
		assertEquals(expected, note);
	}
	
	@Test
	public void testUpdateNote(){
		NoteDao noteDao = new NoteDao();
		
		assertTrue(noteDao.updateNote(14.0, 1, 1));
	}

}
