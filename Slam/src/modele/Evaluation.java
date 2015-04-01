package modele;

public class Evaluation {
	
	private int idEvaluation;
	private int idModule;
	private int idSession;
	private int idFormateur;
	
	public Evaluation(){
		
	}
	
	public Evaluation(int idEvaluation, int idModule, int idSession,
			int idFormateur) {
		super();
		this.idEvaluation = idEvaluation;
		this.idModule = idModule;
		this.idSession = idSession;
		this.idFormateur = idFormateur;
	}
	
	public int getIdEvaluation() {
		return idEvaluation;
	}
	public void setIdEvaluation(int idEvaluation) {
		this.idEvaluation = idEvaluation;
	}
	public int getIdModule() {
		return idModule;
	}
	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	public int getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	@Override
	public String toString() {
		return "EvaluationBean [idEvaluation=" + idEvaluation + ", idModule="
				+ idModule + ", idSession=" + idSession + ", idFormateur="
				+ idFormateur + "]";
	}
	
	
	
}
