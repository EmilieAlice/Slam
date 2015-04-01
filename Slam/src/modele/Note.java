package modele;

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

}
