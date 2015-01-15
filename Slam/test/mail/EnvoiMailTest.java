package mail;



import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class EnvoiMailTest {

	public static  void main(String[] args) throws AddressException, MessagingException{
		String texte ="contenu mail";
		String objet ="objet du mail";
		String adresse="adresse to";
		String gmail="adresse expediteur";
		String mdp="MDP";

		EnvoiMail message = new EnvoiMail();
		message.initialisation(gmail, mdp);
		message.preparation(adresse, objet, texte);
		message.envoiMail();








	}

}
