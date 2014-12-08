package mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mail.EnvoiMail;



public class EnvoiMailTest {
	
	public static void main(String[] args) throws AddressException, MessagingException {
	String texte ="TEST MAIL BTS SIO";
	String adresse="acerdove@hotmail.fr";

	
	EnvoiMail.envoiMail(adresse, texte); 
	
	
	
	
	
	
	
	}
	
}
