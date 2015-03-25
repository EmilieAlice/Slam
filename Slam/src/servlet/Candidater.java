package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("candidater");
			Map<String, String> sessions = MapDao.getSessions();
			request.setAttribute("sessions", sessions);
			System.out.println(sessions.size() + " sessions");
			request.getRequestDispatcher("candidater.jsp").forward(request, response);
		}
		catch (SQLException exc) {
			// renvoyer vers erreur.jsp
		}
		
		HttpSession maSession = request.getSession();
		//maSession = null;
		
		if (maSession != null) {
			
		}
		else{
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		String renvoiForm = null;
		formOk = true; //à vérifier
		personne = new Personne();
		try{
			verifierFormCandidature(request);
			System.out.println(personne.getIdPersonne());
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		if(!formOk){
			// Les messages ont ete positionnes,
			// renvoyons vers le formulaire
			renvoiForm = "candidater.jsp";		
		}
		else{
			String validerCandidature = null;
			PersonneHome personneHome = new PersonneHome();
			try{
				personneHome.insererCandidature();
				EnvoiMail.envoyer(request.getParameter("mail"), "Votre candidature", "M(Mme)"+ ". " +request.getParameter("prenom") 
						+ " " + request.getParameter("nom") +", "
						+"nous avons bien reçu votre candidature, elle sera traitée dans les plus brefs delais.");
				validerCandidature = "La candidature a été pris en compte, un mail de confirmation a été envoyé.";
			}
			catch (AddressException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
			} 
			catch (MessagingException e) {
				e.printStackTrace();
				validerCandidature = e.getMessage();
			}
			request.setAttribute("validerCandidature", validerCandidature);
			renvoiForm = "/WEB-INF/confirmation_candidature.jsp";
		}
		request.getRequestDispatcher(renvoiForm).forward(request, response);
	}
	
	private void verifierFormCandidature(HttpServletRequest request) throws SQLException{
		// Recuperation des parametres
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		String cv = request.getParameter("cv");
		String lm = request.getParameter("lm");
		String msg = request.getParameter("msg");
		
		//vérifier si les champs sont valides
		if(nom.matches("^ *")){
			formOk = false;
			request.setAttribute("msgNom", "Le nom est obligatoire");
		}
		if(prenom.matches("^ *")){
			formOk = false;
			request.setAttribute("msgPrenom", "Le prénom est obligatoire");
		}
		if(mail.matches("^ *")){
			formOk = false;
			request.setAttribute("msgMail", "L'Email est obligatoire");
		}
		if(cv.matches("^ *")){
			formOk = false;
			request.setAttribute("msgCv", "Veuillez joindre Votre CV");
		}
		if(lm.matches("^ *")){
			formOk = false;
			request.setAttribute("msgLm", "Veuillez joindre Votre lettre de motivation");
		}
		/*PersonneHome personneBase = new PersonneHome();
		if (personneBase.checkIdPersonne(personne.getIdPersonne())){
			formOk = false;
			request.setAttribute("msgMail", "L'Email "+ mail + " Vous avez déjà postulé à cette offre");
		}*/
	}

}
