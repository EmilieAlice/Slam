package dao;

import java.sql.ResultSet;

import modele.Formateur;
import modele.Module;
import modele.Personne;

public class FormateurDao {

	private static java.sql.PreparedStatement pFindFormateurByIdPersonne = null;
	/**
	 * Requete pour récupérer un formateur grâce à l'id de la personne
	 */
	static {
		try {
			pFindFormateurByIdPersonne = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.formateur "
									+ "INNER JOIN personne on personne.id_personne = formateur.id_personne "
									+ "WHERE formateur.id_personne = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findFormateurByIdPersonne échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Formateur
	 * 
	 * @param un
	 *            objet Personne
	 * @return un objet Formateur
	 */
	public Formateur findFormateurByIdPersonne(Personne personne) {
		Formateur formateur = new Formateur();
		try {
			pFindFormateurByIdPersonne.setInt(1, personne.getIdPersonne());
			ResultSet resultat = pFindFormateurByIdPersonne.executeQuery();
			if (resultat.next()) {
				formateur.setIdPersonne(resultat.getInt("id_personne"));
				formateur.setDateEntree(resultat.getDate("date_entree"));
				formateur.setIdModule(resultat.getInt("id_module"));
				// Ajoutez les attributs de la personne
				return formateur;
			} else {
				return formateur = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static java.sql.PreparedStatement pFindFormateurByIdModule = null;
	/**
	 * Requete pour récupérer un formateur grâce à l'id de la personne
	 */
	static {
		try {
			pFindFormateurByIdModule = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.formateur "
									+ "INNER JOIN personne on personne.id_personne = formateur.id_personne "
									+ "WHERE formateur.id_module = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findByIdModule échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Formateur
	 * 
	 * @param un
	 *            objet Module
	 * @return un objet Formateur
	 */
	public Formateur findFormateurByIdModule(Module module) {
		Formateur formateur = new Formateur();
		try {
			pFindFormateurByIdModule.setInt(1, module.getIdModule());
			ResultSet resultat = pFindFormateurByIdPersonne.executeQuery();
			if (resultat.next()) {
				formateur.setIdPersonne(resultat.getInt("id_personne"));
				formateur.setDateEntree(resultat.getDate("date_entree"));
				formateur.setIdModule(resultat.getInt("id_module"));
				formateur.setCivilite(resultat.getString("civilite"));
				formateur.setNom(resultat.getString("nom"));
				formateur.setPrenom(resultat.getString("prenom"));
				formateur.setAdresse(resultat.getString("adresse"));
				formateur.setAdresse(resultat.getString("adresse"));
				formateur.setCodePostal(resultat.getString("code_postal"));
				formateur.setVille(resultat.getString("ville"));
				formateur.setTelephone(resultat.getString("telephone"));
				formateur.setEmail(resultat.getString("email"));
			} else {
				formateur = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formateur;
	}

}
