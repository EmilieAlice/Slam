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
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	static String adresse_gmail;
	static String mdp;
	private String adresseTo;
	private String objet ; 
	private String messageText;

	public void setObjet(String objet) {
		this.objet = objet;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public void setAdresseTo(String adresseTo) {
		this.adresseTo = adresseTo;
	}
	public static void setMdp(String mdp) {
		EnvoiMail.mdp = mdp;
	}
	public static void setAdresse_gmail(String adresse_gmail) {
		EnvoiMail.adresse_gmail = adresse_gmail;
	}
	
	public String getAdresseTo() {
		return adresseTo;
	}
	public String getMessageText() {
		return messageText;
	}
	public String getObjet() {
		return objet;
	}
	public static String getMdp() {
		return mdp;
	}
	public static String getAdresse_gmail() {
		return adresse_gmail;
	}

	/** Initialisation des données de connection pour les expeditions de mail,
	 * adresse mail avec mot de passe du compte gmail 
	 * @param adresse gmail de l'administrateur
	 * @param mot de passe de la boite mail
	 */
	public void initialisation(String adresse_gmail, String mdp) {
		setAdresse_gmail(adresse_gmail);
		setMdp(mdp);
		setAdresseTo(adresse_gmail);
		}
		
	/** Preparation du mail grace à l'adresse du destinataire, objet du mail et son contenu du mail
	 * @param adresse mail du candidat
	 * @param objet du mail
	 * @param le message à envoyer au candidate
	 */
	public void preparation(String adresseTo,String objet , String messageText) {
		setObjet(objet);
		setMessageText(messageText);
		
	}
	
	/** Envoi du mail definitif
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void envoiMail() throws AddressException, MessagingException {

//1		
		System.out.println("\n 1st ===> Mise en place des propriétés du serveur");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Les propriétés du serveur de messagerie ont été mis en place avec succès ..");

//2		
		System.out.println("\n\n 2nd ===> Ouverture de la session mail et envoi");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseTo));
			
//Mail
		generateMailMessage.setContent(messageText, "text/html");
		generateMailMessage.setSubject(objet);
		System.out.println("La session a été créée avec succès ..");
		
		Transport transport = getMailSession.getTransport("smtp");
		
// Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
		transport.connect("smtp.gmail.com", adresse_gmail, mdp);
		
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		
		transport.close();
		System.out.println("\n\n ===> Mail envoyé");
	}
}