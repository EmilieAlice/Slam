package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonneHome;
import mail.EnvoiMail;
import modele.Personne;

/**
 * Servlet implementation class Formulaire
 */
public class Inscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscrire() {
		super();
	}

	/**
	 * @see Méthode doGet qui renvoie vers la page de formulaire
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/FormulaireInscription.html").forward(request, response);
	}

	/**
	 * @see Méthode doPost qui récupère les champs du formulaire et qui insère la personne dans la base de données.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coucou");
		/*String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String telephone2 = request.getParameter("telephone2");
		String email = request.getParameter("email");
		String motPasse = request.getParameter("motDePasse");
		boolean estInscrite = false;

		String msg = null;

		Personne personne = new Personne(0, civilite, prenom, nom, adresse, codePostal, ville, telephone, telephone2, email, motPasse, null, estInscrite);
    System.out.println(personne);

		PersonneHome pDAO = new PersonneHome();
		try {
      assert personne.getCivilite() != null;
			pDAO.insert(personne);
			EnvoiMail.envoyer(email, "Votre inscription sur Agriotes", "Veuillez cliquer sur le lien ci-joint pour confirmer votre inscription");
			msg = "Un mail vous a été envoyé. Vous pouvez confirmer votre inscription.";
		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (AddressException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (MessagingException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);*/
	}

}
