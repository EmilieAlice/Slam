package modele;

public class Session {
	
	private int idSession;
	private String nom;
	
	public Session(){
		
	}
	
	public Session(int idSession, String nom) {
		super();
		this.idSession = idSession;
		this.nom = nom;
	}
	
	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Session [idSession=" + idSession + ", nom=" + nom + "]";
	}
	
	

}
