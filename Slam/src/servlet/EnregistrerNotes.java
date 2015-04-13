package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contexte.SessionAgriotes;
import dao.NoteDao;
import dao.PersonneDao;
import dao.SessionDao;
import dao.StagiaireDao;
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
		PersonneDao userDao = new PersonneDao();
		Personne user = null;

		try {
			user = userDao.findById(4);
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
			StagiaireDao stagiaireDao = new StagiaireDao();
			listeStagiaires = stagiaireDao.findStagiaire(idSession);

			HashMap<Stagiaire, Double> noteParStagiairePourCetteEvaluation = new HashMap<Stagiaire, Double>();
			for (Stagiaire stagiaire : listeStagiaires) {
				NoteDao noteDao = new NoteDao();
				Double noteStagiaire = new Double(
						noteDao.recupereNoteStagaire(
								stagiaire.getIdStagiaire(),
								maSession.getIdEvaluation()));
				if (noteStagiaire != 0) {
					noteParStagiairePourCetteEvaluation.put(stagiaire,
							noteStagiaire);
				}
			}

			String nomSession = null;
			SessionDao sessionDao = new SessionDao();
			nomSession = sessionDao.recupereleNomDeLaSession(idSession);

			request.setAttribute("listeStagiaires", listeStagiaires);
			request.setAttribute("idEvaluation", maSession.getIdEvaluation());
			request.setAttribute("idSession", idSession);
			request.setAttribute("nomSession", nomSession);
			request.setAttribute("noteParStagiairePourCetteEvaluation",
					noteParStagiairePourCetteEvaluation);

			request.getRequestDispatcher("/WEB-INF/enregistrementNotes.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("accueil.html").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneDao userDao = new PersonneDao();
		Personne user;

		String renvoiFormulaire = null;

		String session = request.getParameter("idSession");
		int idSession = Integer.parseInt(session);
		String evaluation = request.getParameter("idEvaluation");
		int idEvaluation = Integer.parseInt(evaluation);

		String nomSession = null;
		SessionDao sessionDao = new SessionDao();
		nomSession = sessionDao.recupereleNomDeLaSession(idSession);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireDao stagiaireDao = new StagiaireDao();
		listeStagiaires = stagiaireDao.findStagiaire(idSession);

		HashMap<String, String> listeErreur = new HashMap<String, String>();
		HashMap<Integer, Double> listeNotes = new HashMap<Integer, Double>();

		HashMap<Stagiaire, Double> noteParStagiairePourCetteEvaluation = new HashMap<Stagiaire, Double>();

		for (Stagiaire stagiaire : listeStagiaires) {
			String valeur = request.getParameter(stagiaire.getNom());

			NoteDao noteDao = new NoteDao();
			Double noteStagiaire = new Double(noteDao.recupereNoteStagaire(
					stagiaire.getIdStagiaire(), idEvaluation));

			if (noteStagiaire != 0) {
				noteParStagiairePourCetteEvaluation.put(stagiaire,
						noteStagiaire);
			}
			// vérifier si les champs sont valides
			boolean testLettresVirgule = Pattern.matches("[0-9]{1,2},[0-9]",
					valeur);
			boolean testLettresPoint = Pattern.matches("[0-9]{1,2}.[0-9]",
					valeur);
			boolean testLettresSimple = Pattern.matches("[0-9]{1,2}", valeur);

			if (valeur.matches("^ *")) { // On vérfie que le champs n'est pas
											// vide
				listeErreur.put(stagiaire.getNom(),
						"Vous devez rentrer une note pour ce stagiaire");

			} else if (testLettresSimple) { // Si le champs ne contient pas de
											// lettres au format sans virgule ou
											// point
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
			} else if (!testLettresVirgule && !testLettresPoint) { // On vérifie
																	// qu'il n'y
																	// a pas de
																	// lettre
																	// pour le
																	// format
																	// avec
																	// point et
																	// virgule
				listeErreur
						.put(stagiaire.getNom(),
								"Vous devez rentrer une note pour ce stagiaire au format x.x ou x,x");

			} else { // Si le champs n'est pas vide et ne contient pas de
						// lettres
				if (testLettresVirgule) {// Si il est avec une virgule on le
											// change
					Pattern pattern = Pattern
							.compile("([0-9]{1,2})(,)([0-9]{1})");
					Matcher matcher = pattern.matcher(valeur);
					String partieUn = "";
					String partieTrois = "";
					while (matcher.find()) {
						partieUn = matcher.group(1);
						partieTrois = matcher.group(3);
					}
					valeur = partieUn + "." + partieTrois;
				}
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
		} else if (listeErreur.size() == 0
				&& noteParStagiairePourCetteEvaluation.size() != 0) {
			NoteDao noteDao = new NoteDao();
			for (Entry<Integer, Double> entree : listeNotes.entrySet()) {
				noteDao.updateNote(entree.getValue(), idEvaluation,
						entree.getKey());
			}
			renvoiFormulaire = "/WEB-INF/enregistrementNotesOk.jsp";
		} else {
			for (Entry<Integer, Double> entree : listeNotes.entrySet()) {
				NoteDao noteDao = new NoteDao();
				noteDao.insertNote(idEvaluation, entree.getKey(),
						entree.getValue());
				renvoiFormulaire = "/WEB-INF/enregistrementNotesOk.jsp";
			}
		}

		request.setAttribute("listeStagiaires", listeStagiaires);
		request.setAttribute("idEvaluation", idEvaluation);
		request.setAttribute("idSession", session);
		request.setAttribute("nomSession", nomSession);
		request.setAttribute("noteParStagiairePourCetteEvaluation",
				noteParStagiairePourCetteEvaluation);

		request.getRequestDispatcher(renvoiFormulaire).forward(request,
				response);

	}

}
