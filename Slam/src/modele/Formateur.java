package modele;

import java.sql.Date;

public class Formateur extends Personne {

	private int idFormateur;
	private Date dateEntree;
	private int idModule;

	public Formateur() {

	}

	public Formateur(int idPersonne, Date dateEntree, int idModule) {
		super();
		this.idFormateur = idPersonne;
		this.dateEntree = dateEntree;
		this.idModule = idModule;
	}

	public int getIdPersonne() {
		return idFormateur;
	}

	public void setIdPersonne(int idPersonne) {
		this.idFormateur = idPersonne;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	@Override
	public String toString() {
		return "Formateur [idFormateur=" + idFormateur + ", dateEntree="
				+ dateEntree + ", idModule=" + idModule + "]";
	}

}
