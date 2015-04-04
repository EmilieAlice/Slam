package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contexte.SessionAgriotes;
import dao.NoteHome;
import dao.PersonneHome;
import dao.SessionHome;
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

		// Recuperer la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 4 est connecté
		PersonneHome dao = new PersonneHome();
		Personne user = null;

		try {
			user = dao.findById(4);
			maSession.setUser(user);
			maSession.setIdSession(1);
			request.setAttribute("user", user);
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}
		if (user != null) {

			// String session = request.getParameter("idSession");
			// int idSession = Integer.parseInt(session);
			int idSession = maSession.getIdSession();
			if (maSession.getIdEvaluation() == 0) {
				String evaluation = request.getParameter("evaluation");
				int idEvaluation = Integer.parseInt(evaluation);
				maSession.setIdEvaluation(idEvaluation);
			}

			ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
			StagiaireHome stagiaireDao = new StagiaireHome();
			listeStagiaires = stagiaireDao.findStagiaire(idSession);

			String nomSession = null;
			SessionHome sessionDao = new SessionHome();
			nomSession = sessionDao.recupereleNomDeLaSession(idSession);

			request.setAttribute("listeStagiaires", listeStagiaires);
			request.setAttribute("idEvaluation", maSession.getIdEvaluation());
			request.setAttribute("idSession", idSession);
			request.setAttribute("nomSession", nomSession);

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

		String nomSession = null;
		SessionHome sessionDao = new SessionHome();
		nomSession = sessionDao.recupereleNomDeLaSession(idSession);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(idSession);

		HashMap<String, String> listeErreur = new HashMap<String, String>();
		HashMap<Integer, Double> listeNotes = new HashMap<Integer, Double>();

		for (Stagiaire stagiaire : listeStagiaires) {
			String valeur = request.getParameter(stagiaire.getNom());

			// vérifier si les champs sont valides
			if (valeur.matches("^ *")) { // On vérfie que le champs n'est pas
											// vide
				listeErreur.put(stagiaire.getNom(),
						"Vous devez rentrer une note pour ce stagiaire");
			} else if (!valeur.matches("^ *")) { // Si le champs n'est pas vide
				Double valeurNum = Double.parseDouble(valeur);
				if (valeurNum < 0 || valeurNum > 20) { // On vérfie que la
														// valeur est bien
														// comprise entre 0 et
														// 20
					listeErreur.put(stagiaire.getNom(),
							"Vous devez rentrer une note comprise en 0 et 20");
				} else { // Sinon on enregistre la note
					Double note = Double.parseDouble(valeur);
					listeNotes.put(stagiaire.getIdStagiaire(), note);
				}
			}
		}
		if (listeErreur.size() != 0) {
			request.setAttribute("listeErreur", listeErreur);
			renvoiFormulaire = "/WEB-INF/enregistrementNotes.jsp";
		} else {
			for (Entry<Integer, Double> entree : listeNotes.entrySet()) {
				NoteHome noteDao = new NoteHome();
				noteDao.insertNote(idEvaluation, entree.getKey(),
						entree.getValue());
				renvoiFormulaire = "/WEB-INF/enregistrementNotesOk.jsp";
			}
		}

		request.setAttribute("listeStagiaires", listeStagiaires);
		request.setAttribute("idEvaluation", idEvaluation);
		request.setAttribute("idSession", session);
		request.setAttribute("nomSession", nomSession);

		request.getRequestDispatcher(renvoiFormulaire).forward(request,
				response);

	}

}
