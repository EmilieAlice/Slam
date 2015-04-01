package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NoteHome {

	private static java.sql.PreparedStatement pInsertNote = null;
	/**
	 * Requete pour récupérer une session grâce à son nom
	 */
	static {
		try {
			pInsertNote = DataBase.getConnection().prepareStatement(
					"INSERT INTO lagarenne2015.note VALUES (?,?,?);");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete insertNote échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Session
	 * 
	 * @param nom
	 * @return session
	 */
	public Boolean insertNote(int idPersonne, int idEvaluation, double note) {
		Boolean etat = new Boolean(false);
		try {
			pInsertNote.setInt(1, idPersonne);
			pInsertNote.setInt(2, idEvaluation);
			pInsertNote.setDouble(3, note);
			int resultat = pInsertNote.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

	private static java.sql.PreparedStatement pRecupereNoteParMatiere = null;
	/**
	 * Requete pour récupérer une liste de notes grâce à l'id de la personne
	 */
	static {
		try {
			pRecupereNoteParMatiere = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT module.nom, note, personne.nom FROM lagarenne2015.note "
									+ "INNER JOIN evaluation on note.id_evaluation = evaluation.id_evaluation "
									+ "INNER JOIN module on module.id_module = evaluation.id_module "
									+ "INNER JOIN personne on note.id_personne = personne.id_personne "
									+ "WHERE note.id_personne = ? "
									+ "AND module.id_module = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete recupereNote échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une liste de notes en fonction
	 * de l'id du stagiaire
	 * 
	 * @param idPersonne
	 *            l'id d'une personne
	 * @return collection de notes
	 */
	public ArrayList<Double> recupereNote(int idPersonne, int idModule) {
		ArrayList<Double> listeDeNote = new ArrayList<Double>();
		try {
			pRecupereNoteParMatiere.setInt(1, idPersonne);
			pRecupereNoteParMatiere.setInt(2, idModule);
			ResultSet resultat = pRecupereNoteParMatiere.executeQuery();
			while (resultat.next()) {
				Double note;
				note = resultat.getDouble("note");
				listeDeNote.add(note);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeDeNote;
	}

}
