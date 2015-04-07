package modele;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Seance {

	private int idModule;
	private int idSession;
	private int idFormateur;
	private GregorianCalendar jour;
	private int idSalle;
	private String contenu;
	private Creneau creneau;

	/**
	 * Le jour et le contenu sont null
	 */
	public Seance() {

	}

	public Seance(int idModule, int idSession, int idFormateur,
			GregorianCalendar jour, Creneau creneau, int idSalle, String contenu) {
		super();
		this.idModule = idModule;
		this.idSession = idSession;
		this.idFormateur = idFormateur;
		this.jour = jour;
		this.creneau = creneau;
		this.idSalle = idSalle;
		this.contenu = contenu;
		
		long journee = jour.getTimeInMillis();
		long millis = (3600000);
		long debutSeance = 0;
		if (getCreneau() == Creneau.APRES_MIDI){
			debutSeance = 14 * millis;
		}
		else {
			debutSeance = 9 * millis;
		}
		journee = journee + debutSeance;
		jour.setTimeInMillis(journee);
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

	public GregorianCalendar getJour() {
		return jour;
	}

	public void setJour(GregorianCalendar jour) {
		this.jour = jour;
		long journee = jour.getTimeInMillis();
		long millis = (3600000);
		long debutSeance = 0;
		if (getCreneau() == Creneau.APRES_MIDI){
			debutSeance = 14 * millis;
		}
		else {
			debutSeance = 9 * millis;
		}
		journee = journee + debutSeance;
		jour.setTimeInMillis(journee);
	}
	
	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public enum Creneau {
		MATIN(0), APRES_MIDI(1);

		private int valeur;

		private Creneau(int valeur) {
			this.valeur = valeur;
		}

		public void setValeur(int valeur) {
			this.valeur = valeur;
		}

		public int getValeur() {
			return valeur;
		}
	}

	@Override
	public String toString() {
		return "Seance [idModule= " + idModule + ", idSession= " + idSession
				+ ", idFormateur = " + idFormateur + ", jour = " + jour.getTime()
				+ ", creneau = " + creneau + ", idSalle = " + idSalle 
				+ ", contenu = " + contenu + "]";
	}

}
