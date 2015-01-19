package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMail {

	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;
	private static String adresse_gmail = "lagarenne2015@gmail.com";
	private static String mdp = "azerty2015";

	public static String getAdresse_gmail() {
		return adresse_gmail;
	}

	public static void setAdresse_gmail(String adresse_gmail) {
		EnvoiMail.adresse_gmail = adresse_gmail;
	}

	public static String getMdp() {
		return mdp;
	}

	public static void setMdp(String mdp) {
		EnvoiMail.mdp = mdp;
	}

	/**
	 * Initialisation des données de connection pour les expeditions de mail :
	 * adresse mail avec mot de passe du compte gmail
	 * 
	 * @param adresse_gmail
	 * @param mdp
	 */
	public void initialisation(String adresse_gmail, String mdp) {

		setAdresse_gmail(adresse_gmail);
		setMdp(mdp);
	}

	/*
	 * Envoi le mail préparé
	 */

	public static void envoyer(String adresseTo, String objet,
			String messageText) throws AddressException, MessagingException {

		// 1
		System.out
				.println("\n 1st ===> Mise en place des propriétés du serveur");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out
				.println("Les propriétés du serveur de messagerie ont été mis en place avec succès ...");

		// 2
		System.out
				.println("\n\n 2nd ===> ouverture de la session mail et envoi");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);

		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(adresseTo));

		// Mail
		generateMailMessage.setContent(messageText, "text/html");
		generateMailMessage.setSubject(objet);
		System.out.println("La session a été créée avec succès ..");

		Transport transport = getMailSession.getTransport("smtp");

		// Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
		transport.connect("smtp.gmail.com", adresse_gmail, mdp);

		transport.sendMessage(generateMailMessage,
				generateMailMessage.getAllRecipients());

		transport.close();
		System.out.println("\n\n ===> mail envoyé");
	}

}