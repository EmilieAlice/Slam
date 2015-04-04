package modele;

import java.util.ArrayList;

public class Note {
	
	private int idEvaluation;
	private int idPersonne;
	private float note;
	
	public Note(){
		
	}
	
	public Note(int idEvaluation, int idPersonne, float note) {
		super();
		this.idEvaluation = idEvaluation;
		this.idPersonne = idPersonne;
		this.note = note;
	}
	
	public int getIdEvaluation() {
		return idEvaluation;
	}
	public void setIdEvaluation(int idEvaluation) {
		this.idEvaluation = idEvaluation;
	}
	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	
	public double calculMoyenne(ArrayList<Double> listeNotes){
		Double moyenne = new Double(0);
		Double total = new Double(0);
		for (Double note : listeNotes) {
			total += note;
		}
		moyenne = total / listeNotes.size();
		return moyenne;
	}

	@Override
	public String toString() {
		return "Note [idEvaluation=" + idEvaluation + ", idPersonne="
				+ idPersonne + ", note=" + note + "]";
	}
	
	

}
