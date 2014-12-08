package modele;

import java.sql.Date;

public class Personne {
	private int idPersonne;
	private Date dateInscription;
	private Boolean estInscrite;
	
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public Boolean getEstInscrite() {
		return estInscrite;
	}
	public void setEstInscrite(Boolean estInscrite) {
		this.estInscrite = estInscrite;
	}
	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public Personne( int idPersonne, Date dateInscription, Boolean estInscrite) {
		super();
		this.idPersonne = idPersonne;
		this.dateInscription = dateInscription;
		this.estInscrite = estInscrite;
	}
	public Personne(Personne a) {
		
	}
	

	
}