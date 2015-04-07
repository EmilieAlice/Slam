package modele;

/**
 * Objet Formation
 * @author Alice
 *
 */
public class Formation {
	private int idFormation;
	private String nomFormation;
	private String descriptionFormation;
	
	public Formation(int idFormation, String nomFormation,
			String descriptionFormation) {
		super();
		this.idFormation = idFormation;
		this.nomFormation = nomFormation;
		this.descriptionFormation = descriptionFormation;
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public String getDescriptionFormation() {
		return descriptionFormation;
	}

	public void setDescriptionFormation(String descriptionFormation) {
		this.descriptionFormation = descriptionFormation;
	}

	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", nomFormation="
				+ nomFormation + ", descriptionFormation="
				+ descriptionFormation + "]";
	}
	
		
}
