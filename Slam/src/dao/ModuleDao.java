package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Module;

public class ModuleDao {

	private static java.sql.PreparedStatement pRecupereLesModules = null;
	/**
	 * Requete pour récupérer une liste de modules
	 */
	static {
		try {
			pRecupereLesModules = DataBase.getConnection()
					.prepareStatement("SELECT * from module");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete recupereLesModules échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données la liste des modules
	 *
	 * @return collection de Module
	 */
	public ArrayList<Module> recupereLesModules() {
		ArrayList<Module> listeDesModules = new ArrayList<Module>();
		try {
			ResultSet resultat = pRecupereLesModules.executeQuery();
			while (resultat.next()) {
				Module unModule = new Module();
				unModule.setIdModule(resultat.getInt("id_module"));
				unModule.setNom(resultat.getString("nom"));
				unModule.setObjectif(resultat.getString("objectif"));
				unModule.setContenu(resultat.getString("contenu"));
				unModule.setNbHeuresAnnuelles(resultat.getInt("nb_heures_annuel"));
				unModule.setPrerequis(resultat.getString("prerequis"));
				listeDesModules.add(unModule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeDesModules;
	}
	
	private static java.sql.PreparedStatement pRecupereLeNombreDeModules = null;
	/**
	 * Requete pour récupérer une le nombre de mobules dans la base de données
	 */
	static {
		try {
			pRecupereLeNombreDeModules = DataBase.getConnection().prepareStatement(
					"SELECT count(id_module) as nbre_modules "
					+ "FROM lagarenne2015.module;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete recupereLeNombreDeModules échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans le nombre de modules dans la base données
	 * 
	 * @return un nombre de module (entier)
	 */
	public int recupereLeNombreDeModules() {
		int nbreDeModules = 0;
		try {
			ResultSet resultat = pRecupereLeNombreDeModules.executeQuery();
			if (resultat.next()) {
				nbreDeModules = resultat.getInt("nbre_modules");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbreDeModules;
	}
	
	private static java.sql.PreparedStatement pFindIdModuleByIdFormateur = null;
	/**
	 * Requete pour récupérer l'id dun module en fonction de l'id Fomrateur
	 */
	static {
		try {
			pFindIdModuleByIdFormateur = DataBase.getConnection().prepareStatement(
					"SELECT id_module FROM lagarenne2015.formateur "
					+ "WHERE id_formateur=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findIdModuleByIdFormateur échouée.");
		}
	}

	/**
	 * Méthode qui récupère l'id dun module en fonction de l'id Fomrateur
	 * 
	 * @return un idModule (entier)
	 */
	public int recupereIdModule(int idFormateur) {
		int idModule = 0;
		try {
			pFindIdModuleByIdFormateur.setInt(1, idFormateur);
			ResultSet resultat = pFindIdModuleByIdFormateur.executeQuery();
			if (resultat.next()) {
				idModule = resultat.getInt("id_module");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idModule;
	}


}
