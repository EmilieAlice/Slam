package mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class test {
	
	public static void main(String[] args) throws AddressException, MessagingException {
	String texte ="TEST MAIL BTS SIO....";
	String adresse="acerdove@hotmail.fr";

	
	EnvoiMail.envoiMail(adresse, texte); 
	
	
	
	
	
	
	
	}
	
}
