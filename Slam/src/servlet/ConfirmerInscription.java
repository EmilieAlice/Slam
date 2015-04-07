package servlet;

import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.EnvoiMail;
import modele.Personne;

/**
 * Confirmer l'inscription d'un candidat suite à un mail de
 * validation.
 */
public class ConfirmerInscription extends HttpServlet {

  /**
	 * Pour utiliser le projet avec des versions anterieurs
	 */
	private static final long serialVersionUID = 1L;
private Personne personne;

  /**
   * Confirmer l'inscription du candidat. Avertit l'utilisateur dans
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
      PersonneDao dao = new PersonneDao();
      boolean ok = dao.validerInscription(email, time);

      if (ok) {

        //----------------------------------------------------
        String vue = null;
        try {
          assert personne.getCivilite() != null;
          msg = "Votre inscription est validée. Vous pouvez accéder à votre "
              + "<a href='http://" + request.getServerName()
              + ":" + request.getServerPort()
              + request.getContextPath()
              + "/EspacePersonnel?email="
              + email
              + "'>espace personnel</a>";
          EnvoiMail.envoyer(
              email,
              "Votre inscription sur Agriotes est validée",
              msg);

        }
        catch (AddressException e) {
          e.printStackTrace();
          msg = e.getMessage();
        }
        catch (MessagingException e) {
          e.printStackTrace();
          msg = e.getMessage();
        }
        request.setAttribute("msg", msg);
        vue = "/WEB-INF/message.jsp";
        request.getRequestDispatcher(vue).forward(request, response);
      }
      else {
        msg = "Le lien d'inscription a expiré. Veuillez vous réinscrire";
      }
    }
    catch (NumberFormatException exc) {
      msg = "La clé indiquée est invalide. Veuillez vous réinscrire";
    }
    catch (SQLException exc) {
      msg = "Problème de connexion à la base de données. Veuillez"
          + " réessayer plus tard." + exc.getMessage();
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
