package servlet;

import dao.PersonneHome;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Confirmer l'inscription d'un candidat suite à un mail
 * de validation.
 */
public class ConfirmerInscription extends HttpServlet {

	/** Confirmer l'inscription du candidat. Avertit l'utilisateur dans
	 * l'attribut msg (ok ou erreur).
	 *
	 * @param requestw
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cle = request.getParameter("cle");
		String email = request.getParameter("email");
		String msg = null;
		
			long ms = Long.parseLong(cle);
			Timestamp time = new Timestamp(ms);
			try {
				PersonneHome dao = new PersonneHome();
			boolean ok = dao.valider(email, time);
			
			if (ok) {
				msg = "Votre inscription est validée. Vous pouvez accéder à votre "
						+ "<a href='EspacePersonnel?email="
						+ email + "'>espace personnel</a>";
			}
			else {
				msg = "La clé indiquée est invalide. Veuillez vous réinscrire";
			}
		}
		catch (NumberFormatException exc) {
			msg = "La clé indiquée est invalide. Veuillez vous réinscrire";
		}
		catch (SQLException exc) {
			msg = "Problème de connexion à la base de données. Veuillez"
					+ " réessayer plus tard."
					+ exc.getMessage();
		}
			
	request.setAttribute("msg", msg);
	String vue = "/WEB-INF/message.jsp";
	request.getRequestDispatcher(vue).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		throw new UnsupportedOperationException();
		
		
	}

}