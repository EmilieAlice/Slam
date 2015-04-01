package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoteHome;
import dao.StagiaireHome;
import modele.Stagiaire;

/**
 * Servlet implementation class EnregistrerNotes
 */
public class EnregistrerNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnregistrerNotes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String session = request.getParameter("idSession");
		int idSession = Integer.parseInt(session);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(idSession);

		request.setAttribute("listeStagiaires", listeStagiaires);
		request.setAttribute("idEvaluation", request.getParameter("evaluation"));
		request.setAttribute("idSession", request.getParameter("idSession"));

		request.getRequestDispatcher("/WEB-INF/enregistrementNotes.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSession = request.getParameter("idSession");
		int intIdSession = Integer.parseInt(idSession);
		String evaluation = request.getParameter("idEvaluation");
		int intIdEvaluation = Integer.parseInt(evaluation);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		StagiaireHome stagiaireDao = new StagiaireHome();
		listeStagiaires = stagiaireDao.findStagiaire(intIdSession);

		for (Stagiaire stagiaire : listeStagiaires) {
			String valeur = request.getParameter(stagiaire.getNom());
			if (valeur.isEmpty()) {
				System.out.println("Valeur vide");
			}
			Double note = Double.parseDouble(valeur);
			NoteHome noteDao = new NoteHome();
			noteDao.insertNote(intIdEvaluation, stagiaire.getIdStagiaire(),
					note);
		}

		request.getRequestDispatcher("/WEB-INF/enregistrementNotesOk.jsp").forward(request,
				response);
	}

}
