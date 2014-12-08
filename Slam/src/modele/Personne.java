package modele;

import java.sql.Timestamp;

public class Personne {

	private int idPersonne;
	private String civilite;
	private String prenom;
	private String nom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String telephone2;
	private String email;
	private String motPasse;
	private Timestamp dateInscription;
	private boolean estInscrite;

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public Timestamp getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}

	public boolean getEstInscrite() {
		return estInscrite;
	}

	public void setEstInscrite(boolean estInscrite) {
		this.estInscrite = estInscrite;
	}

	public Personne(int idPersonne, Timestamp dateInscription) {
		super();
		this.idPersonne = idPersonne;
		this.dateInscription = dateInscription;
	}

	public Personne(int idPersonne, String civilite, String nom, String prenom,
			String adresse, String codePostal, String ville, String telephone,
			String telephone2, String email, String motPasse,
			Timestamp time, boolean estInscrite) {
		super();
		this.idPersonne = idPersonne;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.telephone2 = telephone2;
		this.email = email;
		this.motPasse = motPasse;
		this.dateInscription = time;
		this.estInscrite = estInscrite;
	}
	public Personne(Personne a) {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result
				+ ((dateInscription == null) ? 0 : dateInscription.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (estInscrite ? 1231 : 1237);
		result = prime * result + idPersonne;
		result = prime * result
				+ ((motPasse == null) ? 0 : motPasse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result
				+ ((telephone2 == null) ? 0 : telephone2.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (civilite == null) {
			if (other.civilite != null)
				return false;
		} else if (!civilite.equals(other.civilite))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (dateInscription == null) {
			if (other.dateInscription != null)
				return false;
		} else if (!dateInscription.equals(other.dateInscription))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estInscrite != other.estInscrite)
			return false;
		if (idPersonne != other.idPersonne)
			return false;
		if (motPasse == null) {
			if (other.motPasse != null)
				return false;
		} else if (!motPasse.equals(other.motPasse))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (telephone2 == null) {
			if (other.telephone2 != null)
				return false;
		} else if (!telephone2.equals(other.telephone2))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}


}