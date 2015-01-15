package modele;


import java.sql.Timestamp;
import java.util.Objects;

public class Personne {

	public Personne() {
		super();
	}

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
	public boolean isEstInscrite() {
		return estInscrite;
	}
	public void setEstInscrite(boolean estInscrite) {
		this.estInscrite = estInscrite;
	}

	public Personne(int idPersonne, String civilite, String prenom, String nom,
			String adresse, String codePostal, String ville, String telephone,
			String telephone2, String email, String motPasse,
			Timestamp time, boolean estInscrite) {
		super();
		this.idPersonne = idPersonne;
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
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
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Personne other = (Personne) obj;
    if (this.idPersonne != other.idPersonne) {
      return false;
    }
    if (!Objects.equals(this.civilite, other.civilite)) {
      return false;
    }
    if (!Objects.equals(this.prenom, other.prenom)) {
      return false;
    }
    if (!Objects.equals(this.nom, other.nom)) {
      return false;
    }
    if (!Objects.equals(this.adresse, other.adresse)) {
      return false;
    }
    if (!Objects.equals(this.codePostal, other.codePostal)) {
      return false;
    }
    if (!Objects.equals(this.ville, other.ville)) {
      return false;
    }
    if (!Objects.equals(this.telephone, other.telephone)) {
      return false;
    }
    if (!Objects.equals(this.telephone2, other.telephone2)) {
      return false;
    }
    if (!Objects.equals(this.email, other.email)) {
      return false;
    }
    if (!Objects.equals(this.motPasse, other.motPasse)) {
      return false;
    }
    if (!Objects.equals(this.dateInscription, other.dateInscription)) {
      return false;
    }
    if (this.estInscrite != other.estInscrite) {
      return false;
    }
    return true;
  }

	/** Permet, lorsque l'on fait le test JUnit,
	 * de voir les differences entre ce qui est attendu et le resultat(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", civilite=" + civilite
				+ ", prenom=" + prenom + ", nom=" + nom + ", adresse="
				+ adresse + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", telephone=" + telephone + ", telephone2=" + telephone2
				+ ", email=" + email + ", motPasse=" + motPasse
				+ ", dateInscription=" + dateInscription + ", estInscrite="
				+ estInscrite + "]";
	}

}