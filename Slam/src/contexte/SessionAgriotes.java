package contexte;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modele.Personne;

/** 
 * Rassemble les variables de session http.
 * Usage :
 * <pre>// Lecture dans un doGet() ou un doPost()
 * SessionAgriotes session = SessionAgriotes.get(request);
 * Personne user = session.getUser();
 * // Ecriture
 * session.save();</pre>
 * 
 * @author jamal
 *
 */
public class SessionAgriotes {
	/** Requete http qui va lire et modifier cette session agriotes */
	private HttpServletRequest request;
	
	private Personne user;
	private int idSession;
	private int idEvaluation;

	/** Utilisateur connecté à l'appli Agriotes */
	public Personne getUser() {
		return user;
	}

	/** Changer l'utilisateur connecté à l'appli Agriotes */
	public void setUser(Personne user) {
		this.user = user;
	}
	
	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	
	public int getIdEvaluation() {
		return idEvaluation;
	}

	public void setIdEvaluation(int idEvaluation) {
		this.idEvaluation = idEvaluation;
	}


	/** Instancie une session SessionAgriotes (dans une servlet).
	 */
	public static SessionAgriotes get(HttpServletRequest request) {
		SessionAgriotes result = null;
		// Recupere la session http, en la creant s'il n'y en a pas
		HttpSession session = request.getSession(true);
		if (session.getAttribute("session") != null) {
			 result = (SessionAgriotes) session.getAttribute("session");
		} else {
			result = new SessionAgriotes();
		}
		result.request = request;
		return result;
	}
	
	/** Stocke en session la session agriotes courante */
	public void save() {
		HttpSession session = request.getSession(true);
		session.setAttribute("session", this);
	}
 }
