package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Evaluation;
import dao.EvaluationHome;

/**
 * Servlet implementation class ListeEvaluation
 */
public class ListeEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeEvaluation() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSession = request.getParameter("idSession");
		String idFormateur = request.getParameter("idFormateur");
		int intIdSession = Integer.parseInt(idSession);
		int intIdFormateur = Integer.parseInt(idFormateur);

		ArrayList<Evaluation> liste = new ArrayList<Evaluation>();
		EvaluationHome dao = new EvaluationHome();
		liste = dao.findSession(intIdSession, intIdFormateur);

		request.setAttribute("idSession", idSession);
		request.setAttribute("idFormateur", idFormateur);
		request.setAttribute("listeEvaluation", liste);

		request.getRequestDispatcher("/WEB-INF/evaluation.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
