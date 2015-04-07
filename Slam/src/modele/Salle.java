package modele;

public class Salle {
	private int idSalle;
	private String nom_salle;
	
	public Salle(int idSalle, String nom_salle) {
		super();
		this.idSalle = idSalle;
		this.nom_salle = nom_salle;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getNom_salle() {
		return nom_salle;
	}

	public void setNom_salle(String nom_salle) {
		this.nom_salle = nom_salle;
	}

	@Override
	public String toString() {
		return "Salle [idSalle=" + idSalle + ", nom_salle=" + nom_salle + "]";
	}
	
}
