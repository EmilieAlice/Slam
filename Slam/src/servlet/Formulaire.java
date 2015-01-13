package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonneHome;
import modele.Personne;

/**
 * Servlet implementation class Formulaire
 */
public class Formulaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formulaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prénom");
		String adresse = request.getParameter("adresse");
		String codePostale = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("motDePasse");
		Boolean inscrit = false;
		
		System.out.println(civilite+nom+prenom+adresse+codePostale+ville+tel1+tel2+mail+mdp+inscrit);
		
		/*Personne p = new Personne();
		Timestamp d = new Timestamp(2015, 0, 12, 16, 57, 00, 0);
		p.setCivilite(civilite);
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setAdresse(adresse);
		p.setCodePostal(codePostale);
		p.setVille(ville);
		p.setTelephone(tel1);
		p.setTelephone2(tel2);
		p.setDateInscription(d);
		p.setEstInscrite(inscrit);
		p.setEmail(mail);
		p.setMotPasse(mdp);
		
		PersonneHome pDAO = new PersonneHome();
		try {
			pDAO.insert(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
