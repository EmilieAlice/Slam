package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Personne;
import dao.PersonneDao;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Login() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		traitement(request, response);
	}


	private void traitement(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//récuperation du login et pwd d'un user lors d'une connexion
		String login = request.getParameter("login");
		String pwd = request.getParameter("password");

		PersonneDao dao = new PersonneDao();

		try {
			Personne personneConnect = dao.findByLoginPassword(login, pwd);

			if(personneConnect == null) {
				// rediriger vers la page de login index.jsp AVEC message d'erreur!!
				request.setAttribute("msgErreur", "Mauvaises infos");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				return ;
			}

			request.getSession().setAttribute("utilisateur", personneConnect);
			//redirection vers la page d'accueil
			RequestDispatcher rd = request.getRequestDispatcher("accueil.html");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			// Rediriger vers une page d'erreur technique
		}
	}

	/**private void verifierFormulaire(HttpServletRequest request) {
		// Recuperer les parametres
		
		String login = request.getParameter("login");
		String pwd = request.getParameter("password");
		
		//vérification si les champs sont vides
		if (login.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email est obligatoire");
		}
		if (pwd.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgMotDePasse", "Le mot de passe est obligatoire");
		}
	}*/
}
