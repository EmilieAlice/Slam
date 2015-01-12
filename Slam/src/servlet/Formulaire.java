package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prénom");
		String adresse = request.getParameter("adresse");
		String codePostale = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		
		Personne p = new Personne();
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setAdresse(adresse);
		p.setCodePostal(codePostale);
		p.setVille(ville);
		p.setTelephone(tel1);
		p.setTelephone2(tel2);
		
		PersonneHome pDAO = new PersonneHome();
		try {
			pDAO.insert(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
