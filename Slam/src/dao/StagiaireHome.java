package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Stagiaire;

public class StagiaireHome {

	private static java.sql.PreparedStatement pFindStagiaires = null;
	/**
	 * Requete pour récupérer les stagiaires d'une session grâce à l'id de la
	 * session
	 */
	static {
		try {
			pFindStagiaires = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT stagiaire.nom, stagiaire.prenom, stagiaire.id_personne FROM stagiaire "
									+ "INNER JOIN candidature on candidature.id_personne = stagiaire.id_personne "
									+ "WHERE id_session = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findStagiaires échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données les stagiaires d'une session
	 * grâce à l'id de la session
	 * 
	 * @param idSession
	 *            un entier
	 * @return ArrayListe de Stagiaire
	 */
	public ArrayList<Stagiaire> findStagiaire(int idSession) {
		ArrayList<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
		try {
			pFindStagiaires.setInt(1, idSession);
			ResultSet resultat = pFindStagiaires.executeQuery();
			while (resultat.next()) {
				Stagiaire stagiaire = new Stagiaire();
				stagiaire.setNom(resultat.getString("nom"));
				stagiaire.setPrenom(resultat.getString("prenom"));
				stagiaire.setidStagiaire(resultat.getInt("id_personne"));
				listeStagiaire.add(stagiaire);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeStagiaire;
	}

	private static java.sql.PreparedStatement pVerificationStagiaire = null;
	/**
	 * Requete qui vérifie si la personne est stagiaire
	 */
	static {
		try {
			pVerificationStagiaire = DataBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.stagiaire "
							+ "WHERE id_personne=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findStagiaires échouée.");
		}
	}

	/**
	 * Méthode qui vérifie si la personne est stagiaire
	 * 
	 * @param idPersonne
	 *            un entier
	 * @return etat un booléen
	 */
	public Boolean pVerificationStagiaires(int idPersonne) {
		Boolean etat = new Boolean(false);
		try {
			pVerificationStagiaire.setInt(1, idPersonne);
			ResultSet resultat = pVerificationStagiaire.executeQuery();
			if (resultat.next()) {
				etat = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

	private static java.sql.PreparedStatement pFindSessionPersonne = null;
	/**
	 * Requete qui récupère l'id de la session d'une personne si elle est
	 * stagiaire
	 */
	static {
		try {
			pFindSessionPersonne = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT id_session FROM lagarenne2015.candidature "
									+ "WHERE id_personne = ? AND id_etat_candidature = 'A';");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSessionStagiaire échouée.");
		}
	}

	/**
	 * Méthode qui qui récupère l'id de la session d'une personne si elle est
	 * stagiaire
	 * 
	 * @param idPersonne
	 *            un entier
	 * @return idSession un entier
	 */
	public Integer findSessionPersonne(int idPersonne) {
		Integer idSession = new Integer(0);
		try {
			pFindSessionPersonne.setInt(1, idPersonne);
			ResultSet resultat = pFindSessionPersonne.executeQuery();
			if (resultat.next()) {
				idSession = resultat.getInt("id_session");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSession;
	}

}
