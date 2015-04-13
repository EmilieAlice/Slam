package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.SessionsTask;

import contexte.SessionAgriotes;
import dao.DataBase;
import dao.MapDao;
import dao.PersonneDao;
import mail.EnvoiMail;
import modele.Personne;

/**
 * cette servlet permet aux utilisateurs ayant un compte de déposer leurs
 * candidature pour intégrer une session
 * 
 * @author jamal
 *
 */
public class Candidater extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean formOk;
	private Personne personne;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Candidater() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) la méthode doGet permet de vérifier que le candidat est
	 *      connecté et de le rediriger vers une page après cette vérification
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes session = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneDao dao = new PersonneDao();
		Personne user = session.getUser();
		// User 9 en dur
		try {
			user = dao.findById(9);
			session.setUser(user);
			System.out.println("email : " + user.getEmail());
			System.out.println("getUser : " + session.getUser().getEmail());
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		// Si l'utilisateur est connecte
		if (user != null) {
			try {
				System.out.println("candidater");
				Map<String, String> sessions = MapDao.getSessions();
				request.setAttribute("sessions", sessions);
				request.setAttribute("user", user);
				System.out.println(sessions.size() + " sessions");
				request.getRequestDispatcher("WEB-INF/candidater.jsp").forward(
						request, response);
			} catch (SQLException exc) {
				// renvoyer vers erreur.jsp
			}
		} else {
			request.getRequestDispatcher("accueil.html").forward(request,
					response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) la méthode doPost permet de vérifier le formulaire de
	 *      candidature, envoyer un mail de confirmation si OK et par la suite
	 *      stocker les données dans la base
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Simuler que le candidat 2 est connecte
		PersonneDao userDao = new PersonneDao();
		Personne user;
		try {
			user = userDao.findById(9);
			maSession.setUser(user);
			request.setAttribute("user", user);
			System.out.println("email : " + user.getEmail());
			System.out.println("getUser : " + maSession.getUser().getEmail());
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}

		String renvoiForm = null;
		formOk = true; // Ã  vÃ©rifier
		// personne = new Personne();

		try {
			user = userDao.findById(9);
			verifierFormCandidature(request);
			System.out.println(user.getIdPersonne());

			System.out.println("candidater");
			Map<String, String> sessions = MapDao.getSessions();
			request.setAttribute("sessions", sessions);
			System.out.println(sessions.size() + " sessions");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!formOk) {
			// Les messages ont ete positionnes,
			// renvoyons vers le formulaire
			renvoiForm = "WEB-INF/candidater.jsp";
		} else {
			String validerCandidature = null;
			// PersonneHome personneHome = new PersonneHome();

			try {
				// personneHome.insererCandidature();
				user = userDao.findById(9);
				Connection connexion = DataBase.getConnection();
				PreparedStatement insererCandidature = connexion
						.prepareStatement("INSERT INTO candidature(id_session, id_personne, id_etat_candidature,"
								+ " date_effet, motivation) VALUES(?, ?, 'V', now() , ?)");
				System.out.println(insererCandidature);
				// PreparedStatement StmtInsererCandidature =
				// connexion.prepareStatement(insererCandidature);
				int sessionId = Integer.parseInt(request
						.getParameter("session"));
				insererCandidature.setInt(1, sessionId);
				insererCandidature.setInt(2, user.getIdPersonne());
				// insererCandidature.setString(3, "V");
				insererCandidature.setString(3, request.getParameter("msg"));
				insererCandidature.executeUpdate();
				System.out.println(insererCandidature);
				// ===============
				EnvoiMail
						.envoyer(
								user.getEmail(),
								"Votre candidature",
								user.getCivilite()
										+ ". "
										+ user.getPrenom()
										+ " "
										+ user.getNom()
										+ ", "
										+ "Nous avons bien reçu votre candidature, elle sera traitée dans les plus brefs delais.");
				validerCandidature = "La candidature a été pris en compte, un mail de confirmation a été envoyé.";
			} catch (AddressException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
				System.out.println("erreur adresse");
			} catch (MessagingException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
				System.out.println("erreur message");
			} catch (SQLException exc) {
				// TODO Auto-generated catch block
				if (exc.getErrorCode() == DataBase.DOUBLON) {
					exc.printStackTrace();
					validerCandidature = "Vous avez déjà postulé à cette session.";
				}
				else {
					exc.printStackTrace();
					// Renvoyer vers erreur.msg
				}
			}
			request.setAttribute("validerCandidature", validerCandidature);
			renvoiForm = "/WEB-INF/confirmation_candidature.jsp";
		}
		request.getRequestDispatcher(renvoiForm).forward(request, response);
	}

	private void verifierFormCandidature(HttpServletRequest request)
			throws SQLException {
		// Recuperation des parametres
		String session = request.getParameter("session");
		String cv = request.getParameter("cv");
		String lm = request.getParameter("lm");
		String msg = request.getParameter("msg");

		// vÃ©rifier si les champs sont valides
		if (cv.matches("^ *")) {
			formOk = false;
			request.setAttribute("msgCv", "Veuillez joindre Votre CV");
		}
		if (lm.matches("^ *")) {
			formOk = false;
			request.setAttribute("msgLm",
					"Veuillez joindre Votre lettre de motivation");
		}
		/*
		 * PersonneHome personneBase = new PersonneHome(); if
		 * (personneBase.checkIdPersonne(personne.getIdPersonne())){ formOk =
		 * false; request.setAttribute("msgMail", "L'Email "+ mail +
		 * " Vous avez dÃ©jÃ  postulÃ© Ã  cette offre"); }
		 */
	}

}
