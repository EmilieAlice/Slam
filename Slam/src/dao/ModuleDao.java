package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Module;
import modele.Session;

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
				unModule.setNomModule(resultat.getString("nom_module"));
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
	 * Requete pour récupérer le nombre de mobules dans la base de données
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
	 * Requete pour récupérer l'id dun module en fonction de l'id Formateur
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

	private static java.sql.PreparedStatement pFindModuleByNom = null;
	/**
	 * Requete pour récupérer un module grâce à son nom
	 */
	static {
		try {
			pFindModuleByNom = DataBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.module " + "WHERE nom_module=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findModuleByNom échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Module grâce à son nom
	 * 
	 * @param nom
	 * @return Module
	 */
	public Module findModuleByNom(String nom) {
		Module module = new Module();
		try {
			pFindModuleByNom.setString(1, nom);
			ResultSet resultat = pFindModuleByNom.executeQuery();
			if (resultat.next()) {
				module.setIdModule(resultat.getInt("id_module"));
				module.setNomModule(resultat.getString("nom_module"));
				module.setObjectif(resultat.getString("objectif"));
				module.setContenu(resultat.getString("contenu"));
				module.setNbHeuresAnnuelles(resultat.getInt("nb_heures_annuel"));
				module.setPrerequis(resultat.getString("prerequis"));
			} else {
				module = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return module;
	}

	private static java.sql.PreparedStatement pFindModuleAvecHeures = null;
	/**
	 * Requete pour récupérer une collection de modules dont le nombre d'heures
	 * restantes est supérieur à 0
	 */
	static {
		try {
			pFindModuleAvecHeures = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.heures_session_module "
									+ "INNER JOIN module ON heures_session_module.id_module = module.id_module "
									+ "INNER JOIN session ON heures_session_module.id_session = session.id_session "
									+ "HAVING nbre_heures_disponibles > ? "
									+ "AND YEAR(session.date_debut)=? "
									+ "AND session.nom_session=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findModuleAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une collection de modules qui
	 * ont encore des heures disponibles.
	 * 
	 * @param annee
	 * @param session
	 * @return Collection de Module
	 */
	public ArrayList<Module> findModuleAvecHeures(int annee, Session session) {
		ArrayList<Module> listeModule = new ArrayList<Module>();
		try {
			pFindModuleAvecHeures.setInt(1, 0);
			pFindModuleAvecHeures.setInt(2, annee);
			pFindModuleAvecHeures.setString(3, session.getNomSession());
			ResultSet resultat = pFindModuleAvecHeures.executeQuery();
			if (resultat != null) {
				while (resultat.next()) {
					Module unModule = new Module();
					unModule.setIdModule(resultat.getInt("id_module"));
					unModule.setNomModule(resultat.getString("nom_module"));
					unModule.setObjectif(resultat.getString("objectif"));
					unModule.setContenu(resultat.getString("contenu"));
					unModule.setNbHeuresAnnuelles(resultat
							.getInt("nb_heures_annuel"));
					unModule.setPrerequis(resultat.getString("prerequis"));
					listeModule.add(unModule);
				}
			} else {
				listeModule = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeModule;
	}


}
