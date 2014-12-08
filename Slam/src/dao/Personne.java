package dao;

import java.sql.Date;

public class Personne {
private Date dateInscription;
private int id;
	
	public Date getDate_inscription() {
		return dateInscription;
	}

	public void setDate_inscription(Date dateInsc) {
		this.dateInscription = dateInsc;
	}
	
	public Personne(int id_personne, String civilite, String prenom, String nom, String adresse, String code_Postal, String ville, String telephone, String telephone2, String email, String mot_passe, Date date_inscription, boolean est_insrite){
		
	}
	
	public Personne(Date Date_inscription){
		setDate_inscription(Date_inscription);
	}
	public Personne(int id){
		getId(id);
	}
	
		private int getId(int id) {
			return id;			
	}

		public Personne(Personne a) {
		// TODO Auto-generated constructor stub
	}

}
