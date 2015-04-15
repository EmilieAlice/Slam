package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ModuleDao;
import dao.PersonneDao;
import dao.SessionDao;
import modele.Module;
import modele.Personne;
import modele.Session;

/**
 * Servlet implementation class ModificationUtilisateur
 */
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}

	private void traitement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Personne connectedUser = (Personne) request.getSession().getAttribute(
				"utilisateur");
		request.setAttribute("utilisateur", connectedUser);

		// redirection vers la page EditionInfoUtilisateur
		String modifUserBtn = request.getParameter("modifInfoUtilBtn");
		if (modifUserBtn != null && !modifUserBtn.equals("")) {
			request.getRequestDispatcher("/WEB-INF/EditionInfoUtilisateur.jsp")
					.forward(request, response);
			return;
		}

		String inscriptionFormBtn = request.getParameter("inscriptionFormBtn");
		if (inscriptionFormBtn != null && !inscriptionFormBtn.equals("")) {
			PersonneDao pDAO = new PersonneDao();
			try {
				// Récupérer la liste des formateurs
				List<Personne> formateurs = pDAO.retrieveAllFormateurs();
				// Puis mettre la liste en attribut de la requête
				request.setAttribute("formateurs", formateurs);

				// Récupérer la liste des modules
				ModuleDao moduleDAO = new ModuleDao();
				List<Module> modules = moduleDAO.recupereLesModules();
				// Puis mettre la liste en attribut de la requête
				request.setAttribute("modules", modules);

				// Récuprer la liste des sessions
				SessionDao sessionDAO = new SessionDao();
				List<Session> sessions = sessionDAO.retrieveAll();
				// Puis mettre la liste en attribut de la requête
				request.setAttribute("sessions", sessions);

				request.getRequestDispatcher(
						"/WEB-INF/FormulaireGestionFormateur.jsp").forward(
						request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
