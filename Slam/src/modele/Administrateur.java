package modele;

public class Administrateur extends Personne{
	private int idAdmin;
	
	public Administrateur(int idAdmin) {
		super();
		this.idAdmin = idAdmin;
	}

	public Administrateur() {
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	
}
