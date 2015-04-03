package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contexte.SessionAgriotes;
import modele.Module;
import modele.Personne;
import dao.EvaluationHome;
import dao.ModuleHome;
import dao.NoteHome;
import dao.PersonneHome;
import dao.StagiaireHome;

/**
 * Servlet implementation class ListeNotes
 */
public class ListeNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListeNotes() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneHome dao = new PersonneHome();
		Personne user = null;
		// On simule en dur la personne connectée 1
		try {
			user = dao.findById(2);
			maSession.setUser(user);
			request.setAttribute("user", user);
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}
		// String idPersonne = request.getParameter("idPersonne");
		// Integer intIdPersonne = Integer.parseInt(idPersonne);
		if (user != null) {
			int idPersonne = user.getIdPersonne();

			// On vérifie que la personne est bien stagiaire
			StagiaireHome stagiaireDao = new StagiaireHome();
			if (!stagiaireDao.pVerificationStagiaires(idPersonne)) {
				request.setAttribute("verifStagiaire", "Mauvaise");
			}

			// On récupère le nombre maximum d'évaluation pour la session du
			// stagaire
			int idSessionStagiaire;
			idSessionStagiaire = stagiaireDao.findSessionPersonne(idPersonne);
			int nbreMaxEvaluations;
			EvaluationHome evaluationDao = new EvaluationHome();
			nbreMaxEvaluations = evaluationDao
					.findMaxEvaluation(idSessionStagiaire);

			// On récupère tous les modules
			ArrayList<Module> listeDeModule = new ArrayList<Module>();
			ModuleHome moduleDao = new ModuleHome();
			listeDeModule = moduleDao.recupereLesModules();

			// On remplit le dictionnaire de notes pour chaque matière du
			// stagiaire
			HashMap<Module, ArrayList<Double>> listeDeNotesParModule = new HashMap<Module, ArrayList<Double>>();
			NoteHome noteDao = new NoteHome();
			for (Module unModule : listeDeModule) {
				listeDeNotesParModule.put(unModule, noteDao.recupereNote(
						idPersonne, unModule.getIdModule()));
			}

			request.setAttribute("nbreMaxEvaluations", nbreMaxEvaluations);
			request.setAttribute("listeDeModule", listeDeModule);
			request.setAttribute("listeDeNotesParModule", listeDeNotesParModule);

			request.getRequestDispatcher("/WEB-INF/listeNote.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("index.html").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
