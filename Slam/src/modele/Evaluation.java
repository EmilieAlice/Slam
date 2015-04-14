package modele;

import java.util.ArrayList;

public class Evaluation {

	private int idEvaluation;
	private int idModule;
	private int idSession;
	private int idFormateur;
	private ArrayList<Double> listeDesNotes;
	private double noteLaPlusHaute;
	private double noteLaPlusBasse;
	private double moyenne;

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public Evaluation() {

	}

	public double getNoteLaPlusHaute() {
		return noteLaPlusHaute;
	}

	public void setNoteLaPlusHaute(double noteLaPlusHaute) {
		this.noteLaPlusHaute = noteLaPlusHaute;
	}

	public double getNoteLaPlusBasse() {
		return noteLaPlusBasse;
	}

	public void setNoteLaPlusBasse(double noteLaPlusBasse) {
		this.noteLaPlusBasse = noteLaPlusBasse;
	}

	public ArrayList<Double> getListeDesNotes() {
		return listeDesNotes;
	}

	public void setListeDesNotes(ArrayList<Double> listeDesNotes) {
		this.listeDesNotes = listeDesNotes;
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

	public double calculMoyenne() {
		Double moyenne = new Double(0);
		Double total = new Double(0);
		for (Double note : listeDesNotes) {
			total += note;
		}
		moyenne = total / listeDesNotes.size();
		int moyenneArrondie = (int) (moyenne * 10);
		moyenne = (double) (moyenneArrondie);
		moyenne /= 10;
		return moyenne;
	}

	public double calculNoteLaPlusHaute() {
		Double noteLaPlusHaute = new Double(0);
		for (Double note : listeDesNotes) {
			if (note >= noteLaPlusHaute) {
				noteLaPlusHaute = note;
			}
		}
		return noteLaPlusHaute;
	}
	
	public double calculNoteLaPlusBasse() {
		Double noteLaPlusBasse = new Double(20);
		for (Double note : listeDesNotes) {
			if (note <= noteLaPlusBasse) {
				noteLaPlusBasse = note;
			}
		}
		return noteLaPlusBasse;
	}

	@Override
	public String toString() {
		return "Evaluation [idEvaluation=" + idEvaluation + ", idModule="
				+ idModule + ", idSession=" + idSession + ", idFormateur="
				+ idFormateur + ", listeDesNotes=" + listeDesNotes + "]";
	}


}
