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
	static String adresseTo;
	static String objet ; 
	static String messageText;

	

	public static String getAdresseTo() {
		return adresseTo;
	}
	public static void setAdresseTo(String adresseTo) {
		EnvoiMail.adresseTo = adresseTo;
	}
	public String getObjet() {
		return objet;
	}
	public static void setObjet(String objet) {
		EnvoiMail.objet = objet;
	}
	public String getMessageText() {
		return messageText;
	}
	public static void setMessageText(String messageText) {
		EnvoiMail.messageText = messageText;
	}
	public static String getMdp() {
		return mdp;
	}
	public static void setMdp(String mdp) {
		EnvoiMail.mdp = mdp;
	}
	public static String getAdresse_gmail() {
		return adresse_gmail;
	}
	public static void setAdresse_gmail(String adresse_gmail) {
		EnvoiMail.adresse_gmail = adresse_gmail;
	}
	
	
	
	
	
	public void initialisation(String adresse_gmail, String mdp) {
		
		setAdresse_gmail(adresse_gmail);
		setMdp(mdp);
		setAdresseTo(adresse_gmail);
		}
		
		
	
	
	
	public void preparation(String adresseTo,String objet , String messageText) {
		setObjet(objet);
		setMessageText(messageText);
		
	}
	
	
	
	
	public void envoiMail() throws AddressException, MessagingException {

//1		
		System.out.println("\n 1st ===> Mise en place des propri�t�s du serveur");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Les propri�t�s du serveur de messagerie ont �t� mis en place avec succ�s ..");
		
		
 
//2		
		System.out.println("\n\n 2nd ===> ouverture de la session mail et envoi");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseTo));
		
		
		//Mail
	
		
		
		generateMailMessage.setContent(messageText, "text/html");
		generateMailMessage.setSubject(objet);
		System.out.println("session a �t� cr�� avec succ�s ..");
		
	
		Transport transport = getMailSession.getTransport("smtp");
		
		// Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
		transport.connect("smtp.gmail.com", adresse_gmail, mdp);
		
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		

		transport.close();
		System.out.println("\n\n ===> mail evoyer");
	}
}