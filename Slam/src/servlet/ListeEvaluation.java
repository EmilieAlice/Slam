package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contexte.SessionAgriotes;
import modele.Evaluation;
import modele.Personne;
import dao.EvaluationDao;
import dao.ModuleDao;
import dao.PersonneDao;

/**
 * Servlet implementation class ListeEvaluation
 */
public class ListeEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeEvaluation() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Personne user = maSession.getUser();
		// Simuler que le formateir 4 est connecte
		PersonneDao userDao = new PersonneDao();
		Personne user = null;

		// int intIdSession = maSession.getIdSession();
		try {
			user = userDao.findById(5);
			maSession.setUser(user);
			request.setAttribute("user", user);
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}
		if (user != null) {
			String idSession = request.getParameter("idSession");
			// le idFormateur est àA récupérer de l'objet user de la session
			// String idFormateur = request.getParameter("idFormateur");
			// int intIdSession = Integer.parseInt(idSession);
			int intIdSession = 1;
			// int intIdFormateur = Integer.parseInt(idFormateur);
			int idFormateur = user.getIdPersonne();

			// if (user != null) {
			ArrayList<Evaluation> liste = new ArrayList<Evaluation>();
			EvaluationDao evaluationDao = new EvaluationDao();
			liste = evaluationDao.findSession(intIdSession, idFormateur);

			request.setAttribute("idSession", intIdSession);
			request.setAttribute("idFormateur", idFormateur);
			request.setAttribute("listeEvaluation", liste);

			request.getRequestDispatcher("/WEB-INF/evaluation.jsp").forward(
					request, response);

		} else {
			request.getRequestDispatcher("accueil.html").forward(request,
					response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recupere la session dans la variable session
		SessionAgriotes maSession = SessionAgriotes.get(request);
		// Personne user = maSession.getUser();
		// Simuler que le formateur 4 est connecte
		PersonneDao userDao = new PersonneDao();
		Personne user = null;

		// int intIdSession = maSession.getIdSession;
		try {
			user = userDao.findById(5);
			maSession.setUser(user);
			request.setAttribute("user", user);
		} catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}

		if (user != null) {
			ModuleDao moduleDao = new ModuleDao();
			int idModule = moduleDao.recupereIdModule(user.getIdPersonne());
			EvaluationDao evaluationDao = new EvaluationDao();
			evaluationDao.insertEvaluation(idModule, 1, maSession.getUser()
					.getIdPersonne());

			request.setAttribute("idSession", 1);
		}
		response.sendRedirect("/Slam/ListeEvaluation");

	}

}
