package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class Stagiaire {

	private String nom;
	private String prenom;
	private int idStagiaire;

	public Stagiaire() {

	}

	public Stagiaire(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getIdStagiaire() {
		return idStagiaire;
	}

	public void setidStagiaire(int idStagiaire) {
		this.idStagiaire = idStagiaire;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom
				+ ", idStagiaire=" + idStagiaire + "]";
	}

}
