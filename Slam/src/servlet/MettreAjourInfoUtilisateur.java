package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonneDao;
import modele.Personne;

/**
 * Servlet implementation class MettreAjourInfoUtilisateur
 */
public class MettreAjourInfoUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MettreAjourInfoUtilisateur() {
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

		String hasToChangePwd = request.getParameter("enablePwdChangeBtn");
		if (hasToChangePwd != null
				&& hasToChangePwd.equals("Modifier le mot de passe")) {
			request.setAttribute("changePwd", true);
			request.getRequestDispatcher("/WEB-INF/EditionInfoUtilisateur.jsp")
					.forward(request, response);
			return;
		}

		// Enregistrement des modifications

		// String civilite = request.getParameter("civilite");
		// String nom = request.getParameter("nom");
		// String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String telephone2 = request.getParameter("telephone2");

		String mailActuel = "";

		PersonneDao pDAO = new PersonneDao();
		try {
			Personne personneDejaExistante = pDAO.findById(connectedUser
					.getIdPersonne());

			// traiter le changement de mot de passe uniquement si changement
			// activé
			String mdp = "";
			if (request.getParameter("anc_motDePasse") != null) {
				String ancMotPasse = request.getParameter("anc_motDePasse");
				String nMdp = request.getParameter("nouv_motDePasse");
				String confMdp = request.getParameter("conf_motDePasse");

				if (!personneDejaExistante.getMotPasse().equals(ancMotPasse)
						|| !nMdp.equals(confMdp)) {
					String msgMotDePasse = "Mot de passe incohérent";

					request.setAttribute("msgMotDePasse", msgMotDePasse);
					request.setAttribute("changePwd", true);
					request.getRequestDispatcher(
							"/WEB-INF/EditionInfoUtilisateur.jsp").forward(
							request, response);
					return;
				}

				mdp = confMdp;

			}

			mailActuel = personneDejaExistante.getEmail();
			String email = request.getParameter("email");

			if (pDAO.mailDejaUtiliser(email)) {
				if (!mailActuel.equals(email)) {
					request.setAttribute("msgEmail",
							"Ce mail existe déjà en base !");
					
					request.getRequestDispatcher(
							"/WEB-INF/EditionInfoUtilisateur.jsp").forward(
							request, response);
					return;
				}
			}
			
			mailActuel = email;

			if (mdp.isEmpty()) {
				pDAO.updatePersonneInfo(personneDejaExistante.getIdPersonne(),
						adresse, codePostal, ville, telephone, telephone2,
						mailActuel);
			} else {
				pDAO.updatePersonneInfo(personneDejaExistante.getIdPersonne(),
						adresse, codePostal, ville, telephone, telephone2,
						mailActuel, mdp);
			}

			personneDejaExistante = pDAO
					.findById(connectedUser.getIdPersonne());

			request.getSession().setAttribute("utilisateur",
					personneDejaExistante);
			request.getRequestDispatcher("accueil.html").forward(request,
					response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
