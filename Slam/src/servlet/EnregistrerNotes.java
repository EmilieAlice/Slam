package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contexte.SessionAgriotes;
import dao.NoteHome;
import dao.PersonneHome;
import dao.StagiaireHome;
import modele.Personne;
import modele.Stagiaire;

/**
 * Servlet implementation class EnregistrerNotes
 */
public class EnregistrerNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean formOk;
	private Personne personne;

	public EnregistrerNotes() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneHome dao = new PersonneHome();
		Personne user = null;

		try {
			user = dao.findById(4);
			maSession.setUser(user);
			request.setAttribute("user", user);
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}
		if (user != null) {

			String session = request.getParameter("idSession");
			int idSession = Integer.parseInt(session);
			String evaluation = request.getParameter("evaluation");
			int idEvaluation = Integer.parseInt(evaluation);

			ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
			StagiaireHome stagiaireDao = new StagiaireHome();
			listeStagiaires = stagiaireDao.findStagiaire(idSession);

			request.setAttribute("listeStagiaires", listeStagiaires);
			request.setAttribute("idEvaluation", idEvaluation);
			request.setAttribute("idSession", idSession);

			request.getRequestDispatcher("/WEB-INF/enregistrementNotes.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("index.html").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneHome dao = new PersonneHome();
		Personne user;
		
		String renvoiFormulaire = null;
		
		String session = request.getParameter("idSession");
		int idSession = Integer.parseInt(session);
		String evaluation = request.getParameter("idEvaluation");
		int idEvaluation = Integer.parseInt(evaluation);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(idSession);

		for (Stagiaire stagiaire : listeStagiaires) {
			String valeur = request.getParameter(stagiaire.getNom());
			// vérifier si les champs sont valides
			if (valeur.matches("^ *")) {
				request.setAttribute("msgNote", "Vous devez rentrer une note");
				renvoiFormulaire = "/WEB-INF/enregistrementNotes.jsp";
			} else if (valeur.matches("^ *")) {
				request.setAttribute("msgNote",
						"Vous devez rentrer une note comprise en 0 et 20");
				renvoiFormulaire = "/WEB-INF/enregistrementNotes.jsp";
			} else {
				Double note = Double.parseDouble(valeur);
				NoteHome noteDao = new NoteHome();
				noteDao.insertNote(idEvaluation, stagiaire.getIdStagiaire(),
						note);
				renvoiFormulaire = "/WEB-INF/enregistrementNotesOk.jsp";
			}
		}
		
		request.setAttribute("listeStagiaires", listeStagiaires);
		request.setAttribute("idEvaluation", idEvaluation);
		request.setAttribute("idSession", session);
		request.getRequestDispatcher(renvoiFormulaire)
		.forward(request, response);
		
	}

}
