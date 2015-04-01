package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Evaluation;

public class EvaluationHome {

	private static java.sql.PreparedStatement pFindEvaluation = null;
	/**
	 * Requete pour récupérer une liste d'évaluation pour une session et un
	 * formateur
	 */
	static {
		try {
			pFindEvaluation = DataBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.evaluation "
							+ "WHERE id_session=? " + "AND id_formateur=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findEvaluation échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une liste d'évaluation pour une
	 * session et un formateur
	 * 
	 * @param idSession un entier
	 * @param idFormateur un entier
	 * @return un ArrayList d'Evaluation
	 */
	public ArrayList<Evaluation> findSession(int idSession, int idFormateur) {
		ArrayList<Evaluation> listeEvaluation = new ArrayList<Evaluation>();
		try {
			pFindEvaluation.setInt(1, idSession);
			pFindEvaluation.setInt(2, idFormateur);
			ResultSet resultat = pFindEvaluation.executeQuery();
			while (resultat.next()) {
				Evaluation evaluation = new Evaluation();
				evaluation.setIdEvaluation(resultat.getInt("id_evaluation"));
				evaluation.setIdModule(resultat.getInt("id_module"));
				evaluation.setIdSession(idSession);
				evaluation.setIdFormateur(idFormateur);
				listeEvaluation.add(evaluation);
			}
			return listeEvaluation;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEvaluation;
	}

	private static java.sql.PreparedStatement pFindMaxEvaluation = null;
	/**
	 * Requete pour récupérer le nombre maximum d'évaluations
	 */
	static {
		try {
			pFindMaxEvaluation = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT MAX(nbre) AS nbre_max FROM"
									+ "(SELECT COUNT(id_evaluation) AS nbre "
									+ "FROM lagarenne2015.evaluation "
									+ "WHERE id_session = ? "
									+ "GROUP BY id_module) AS nbre_eval_module;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findMaxEvaluation échouée.");
		}
	}

	/**
	 * Méthode qui récupère le nombre maximum d'évaluation
	 * 
	 * @return entier, le nombre maximum d'évaluation
	 */
	public Integer findMaxEvaluation(int idSession) {
		Integer nbreMaxEvaluation = 0;
		try {
			pFindMaxEvaluation.setInt(1, idSession);
			ResultSet resultat = pFindMaxEvaluation.executeQuery();
			while (resultat.next()) {
				nbreMaxEvaluation = resultat.getInt("nbre_max");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbreMaxEvaluation;
	}

}
