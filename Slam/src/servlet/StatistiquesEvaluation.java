package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EvaluationDao;
import modele.Evaluation;

/**
 * Servlet implementation class StatistiquesEvaluation
 */
public class StatistiquesEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatistiquesEvaluation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEvaluation = request.getParameter("evaluation");
		int intIdEvaluation = Integer.parseInt(idEvaluation);
		
		Evaluation evaluation = new Evaluation();
		EvaluationDao evaluationDao = new EvaluationDao();
		
		ArrayList<Double> listeDesNotes = new ArrayList<Double>();
		listeDesNotes = evaluationDao.findListeNotes(intIdEvaluation);
		evaluation.setListeDesNotes(listeDesNotes);
		
		evaluation.setMoyenne(evaluation.calculMoyenne());
		evaluation.setNoteLaPlusHaute(evaluation.calculNoteLaPlusHaute());
		evaluation.setNoteLaPlusBasse(evaluation.calculNoteLaPlusBasse());
		
		request.setAttribute("evaluation", evaluation);
		
		request.getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
