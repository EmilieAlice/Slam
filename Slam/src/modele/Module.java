package modele;

import java.time.Duration;

/**
 * Objet Module(matière)
 * 
 * @author Jerome
 *
 */

public class Module {

	private int idModule;
	private String nomModule;
	private String objectif;
	private String contenu;
	private int nbHeuresAnnuelles;
	private String prerequis;

	public Module() {

	}

	public Module(int idModule, String nomModule, String objectif, String contenu,
			int nbHeuresAnnuelles, String prerequis) {
		super();
		this.idModule = idModule;
		this.nomModule = nomModule;
		this.objectif = objectif;
		this.contenu = contenu;
		this.nbHeuresAnnuelles = nbHeuresAnnuelles;
		this.prerequis = prerequis;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public String getNomModule() {
		return nomModule;
	}

	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getNbHeuresAnnuelles() {
		return nbHeuresAnnuelles;
	}

	public void setNbHeuresAnnuelles(int nbHeuresAnnuelles) {
		this.nbHeuresAnnuelles = nbHeuresAnnuelles;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	@Override
	public String toString() {
		return "Module [id_module=" + idModule + ", nomModule=" + nomModule
				+ ", objectif=" + objectif + ", contenu=" + contenu
				+ ", nb_heures_annuelles=" + nbHeuresAnnuelles
				+ ", prerequis=" + prerequis + "]";
	}

	public Duration getDureeDisponible() {
		throw new UnsupportedOperationException();
	}
}
