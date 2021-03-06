package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Personne;

/**
 * Servlet implementation class GestionFormateur
 */
public class GestionFormateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionFormateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/FormulaireGestionFormateur.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);

	}

	private void traitement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Personne connectedUser = (Personne) request.getSession().getAttribute("utilisateur");
		request.setAttribute("utilisateur", connectedUser);

		//redirection vers la page EditionInfoUtilisateur

		request.getRequestDispatcher("/WEB-INF/validation.html").forward(request, response);

	}

}
