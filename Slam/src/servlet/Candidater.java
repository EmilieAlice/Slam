package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import dao.PersonneHome;
import mail.EnvoiMail;
import modele.Personne;

/**
 * Servlet implementation class Candidater
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
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession;
		HttpSession session = request.getSession(true);
		if (session.getAttribute("session") != null) {
			maSession = (SessionAgriotes) session.getAttribute("session");
		} else {
			maSession = new SessionAgriotes();
		}
		// Remet maSession en session
		session.setAttribute("session", maSession);
		// Simuler que le candidat 2 est connecte
		PersonneHome dao = new PersonneHome();
		Personne user;
		try {
			user = dao.findById(3);
			maSession.setUser(user);
			request.setAttribute("civiliteUser", user.getCivilite());
			request.setAttribute("nomUser", user.getNom());
			request.setAttribute("prenomUser", user.getPrenom());

			System.out.println("email : " + user.getEmail());
			System.out.println("getUser : " + maSession.getUser().getEmail());
			if (maSession.getUser() != null) {
				try {
					System.out.println("candidater");
					Map<String, String> sessions = MapDao.getSessions();
					request.setAttribute("sessions", sessions);
					System.out.println(sessions.size() + " sessions");
					request.getRequestDispatcher("candidater.jsp").forward(
							request, response);
				} catch (SQLException exc) {
					// renvoyer vers erreur.jsp
				}
			} else {

				request.getRequestDispatcher("index.html").forward(request,
						response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//
		String renvoiForm = null;
		formOk = true; // Ã  vÃ©rifier
		personne = new Personne();
		try {
			verifierFormCandidature(request);
			System.out.println(personne.getIdPersonne());

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
			renvoiForm = "candidater.jsp";
		} else {
			String validerCandidature = null;
			PersonneHome personneHome = new PersonneHome();
			try {
				// personneHome.insererCandidature();
				String insererCandidature = "INSERT INTO candidature(id_session, id_personne, id_etat_candidature,"
						+ " date_effet, motivation) VALUES(?, ?, ?, ? , ?)";
				Connection connexion = DataBase.getConnection();
				PreparedStatement StmtInsererCandidature = connexion
						.prepareStatement(insererCandidature);
				int sessionId = Integer.parseInt(request
						.getParameter("session"));
				StmtInsererCandidature.setInt(1, sessionId);

				// ===============
				EnvoiMail
						.envoyer(
								request.getParameter("mail"),
								"Votre candidature",
								"M(Mme)"
										+ ". "
										+ request.getParameter("prenom")
										+ " "
										+ request.getParameter("nom")
										+ ", "
										+ "nous avons bien reÃ§u votre candidature, elle sera traitÃ©e dans les plus brefs delais.");
				validerCandidature = "La candidature a Ã©tÃ© pris en compte, un mail de confirmation a Ã©tÃ© envoyÃ©.";
			} catch (AddressException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
			} catch (MessagingException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
