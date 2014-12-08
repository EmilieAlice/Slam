package mail;

import java.awt.TrayIcon.MessageType;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

 
public class EnvoiMail {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	

 
	public static void envoiMail(String adresseTo, String messageText) throws AddressException, MessagingException {

//1		
		System.out.println("\n 1st ===> Mise en place des propriétés du serveur");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Les propriétés du serveur de messagerie ont été mis en place avec succès ..");
		
		
 
//2		
		System.out.println("\n\n 2nd ===> ouverture de la session mail et envoi");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseTo));
		
		
		//Mail
		String emailBody = messageText;
		
		
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("session a été créé avec succès ..");
		
	
		Transport transport = getMailSession.getTransport("smtp");
		
		// Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
		transport.connect("smtp.gmail.com", "hssainisaad@gmail.com", "lenovo10");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		

		transport.close();
		System.out.println("\n\n ===> mail evoyer");
	}
}