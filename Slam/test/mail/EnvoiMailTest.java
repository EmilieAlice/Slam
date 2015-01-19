package mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class EnvoiMailTest {

	public static void main(String[] args) throws AddressException,
			MessagingException {
		EnvoiMail.envoyer("lebaronjerome@free.fr", "test",
				"Essai depuis la classe de test");
	}

}
