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
	private boolean formIsValid;
	private Personne personne;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscrire() {
		super();
	}

	/**
	 * @see Méthode doGet qui renvoie vers la page de formulaire
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/FormulaireInscription.jsp")
		.forward(request, response);
	}

	/**
	 * @see Méthode doPost qui récupère les champs du formulaire et qui insère
	 *      la personne dans la base de données.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// La JSP a afficher
		String vue = null;

		// Verifier que les champs obligatoires sont là
		formIsValid = true; // optimisme
		verifierFormulaire(request);
		if (!formIsValid) {
			// Les messages ont ete positionnes,
			// renvoyons vers le formulaire
			vue = "/WEB-INF/FormulaireInscription.jsp";
		} else {
			String msg = null;
			PersonneHome pDAO = new PersonneHome();
			try {
				assert personne.getCivilite() != null;
				pDAO.insert(personne);
				EnvoiMail.envoyer(personne.getEmail(),
						"Votre inscription sur Agriotes",
						"Veuillez cliquer sur le lien ci-joint pour "
								+ "confirmer votre inscription");
				msg = "Un mail vous a été envoyé. Vous pouvez "
						+ "confirmer votre inscription.";
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
			vue = "/WEB-INF/message.jsp";
		}
		request.getRequestDispatcher(vue).forward(request, response);
	}

	/**
	 * Verifie que le formulaire est valide : champs requis renseignés, plages
	 * de valeur respectées. Positionne isFormValid. Si valide, crée le bean
	 * personne reflet de la saisie. Sinon, positionne les messages d'erreur de
	 * la forme msgNomDuChamp (ex : msgEmail)
	 *
	 * @param request
	 */
	private void verifierFormulaire(HttpServletRequest request) {
		// Recuperer les parametres
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String telephone2 = request.getParameter("telephone2");
		String email = request.getParameter("email");
		String motPasse = request.getParameter("motDePasse");
		// Date sans importance, elle est fixée par la BD
		Timestamp time = Timestamp.valueOf("2000-01-01 00:00:00.0");
		boolean estInscrite = false;

		// Les tests pour vérifier si les champs sont vides
		if (nom.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgNom", "Le nom est obligatoire");
		}
		if (prenom.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgPrenom", "Le prénom est obligatoire");
		}
		if (email.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email est obligatoire");
		}
		if (motPasse.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgMotDePasse", "Le mot de passe est obligatoire");
		}


		if (formIsValid) {
			personne = new Personne(0, civilite, prenom, nom, adresse,
					codePostal, ville, telephone, telephone2, email, motPasse,
					time, estInscrite);
			System.out.println(personne);
		}
	}

}
