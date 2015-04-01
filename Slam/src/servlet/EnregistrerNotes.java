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
		Personne user;

		String session = request.getParameter("idSession");
		int idSession = Integer.parseInt(session);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(idSession);

		request.setAttribute("listeStagiaires", listeStagiaires);
		request.setAttribute("idEvaluation", request.getParameter("evaluation"));
		request.setAttribute("idSession", request.getParameter("idSession"));

		request.getRequestDispatcher("/WEB-INF/enregistrementNotes.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneHome dao = new PersonneHome();
		Personne user;

		String idSession = request.getParameter("idSession");
		int intIdSession = Integer.parseInt(idSession);
		String evaluation = request.getParameter("idEvaluation");
		int intIdEvaluation = Integer.parseInt(evaluation);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(intIdSession);

		for (Stagiaire stagiaire : listeStagiaires) {
			String valeur = request.getParameter(stagiaire.getNom());
			if (valeur.isEmpty()) {
				System.out.println("Valeur vide");
			}
			Double note = Double.parseDouble(valeur);
			NoteHome noteDao = new NoteHome();
			noteDao.insertNote(intIdEvaluation, stagiaire.getIdStagiaire(),
					note);
		}

		request.getRequestDispatcher("/WEB-INF/enregistrementNotesOk.jsp")
				.forward(request, response);
	}

	private void verifierFormNotes(HttpServletRequest request)
			throws SQLException {
		// Recuperation des parametres
		String session = request.getParameter("session");
		String valeur = request.getParameter(stagiaire.getNom());

		// vérifier si les champs sont valides
		if (valeur.matches("^ *")) {
			formOk = false;
			request.setAttribute("msgValeur", "Vous devez rentrer un note");
		} else if (valeur.matches("^ *")) {
			formOk = false;
			request.setAttribute("msgValeur",
					"Vous devez rentrer une note comprise en 0 et 20");
		}

	}
}
