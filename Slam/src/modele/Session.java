package modele;

import java.util.GregorianCalendar;

/**
 * Objet Session
 * 
 */
public class Session {

	private int idSession;
	private String nomSession;
	private GregorianCalendar dateDebut;
	private GregorianCalendar dateFin;
	private String descriptionSession;
	private int idFormation;
	private GregorianCalendar dateDebutInscription;
	private GregorianCalendar dateFinInscription;

	public Session() {

	}

	public Session(int idSession, String nomSession, GregorianCalendar dateDebut, GregorianCalendar dateFin,
			String descriptionSession, int idFormation, GregorianCalendar dateDebutInscription,
			GregorianCalendar dateFinInscription) {
		super();
		this.idSession = idSession;
		this.nomSession = nomSession;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.descriptionSession = descriptionSession;
		this.idFormation = idFormation;
		this.dateDebutInscription = dateDebutInscription;
		this.dateFinInscription = dateFinInscription;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public String getNomSession() {
		return nomSession;
	}

	public void setNomSession(String nomSession) {
		this.nomSession = nomSession;
	}

	public GregorianCalendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(GregorianCalendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public GregorianCalendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(GregorianCalendar dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescriptionSession() {
		return descriptionSession;
	}

	public void setDescriptionSession(String descriptionSession) {
		this.descriptionSession = descriptionSession;
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public GregorianCalendar getDateDebutInscription() {
		return dateDebutInscription;
	}

	public void setDateDebutInscription(GregorianCalendar dateDebutInscription) {
		this.dateDebutInscription = dateDebutInscription;
	}

	public GregorianCalendar getDateFinInscription() {
		return dateFinInscription;
	}

	public void setDateFinInscription(GregorianCalendar dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	@Override
	public String toString() {
		return "Session [id_session=" + idSession + ", nomSession=" + nomSession
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", descriptionSession=" + descriptionSession + ", id_formation="
				+ idFormation + ", dateDebutInscription="
				+ dateDebutInscription + ", dateFinInscription="
				+ dateFinInscription + "]";
	}

}
