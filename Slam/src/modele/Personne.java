package modele;

import java.sql.Date;

public class Personne {
	private int idPersonne;
	private Date dateInscription;
	private boolean estInscrite;

	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public boolean getEstInscrite() {
		return estInscrite;
	}
	public void setEstInscrite(boolean estInscrite) {
		this.estInscrite = estInscrite;
	}


	public Personne( int idPersonne, Date dateInscription) {
		super();
		this.idPersonne = idPersonne;
		this.dateInscription = dateInscription;
	}
	public Personne(Personne a) {

	}


}