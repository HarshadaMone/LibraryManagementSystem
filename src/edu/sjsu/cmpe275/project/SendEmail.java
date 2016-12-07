package edu.sjsu.cmpe275.project;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void sendEmail(String to, String from,int activationCode) {

			final String username = from;
			final String password = "Dhvanit@87";
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });
			
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
				message.setSubject("User Authentication");
				message.setText("Dear User,"
					+ "\n\n Following is your authentication code!"
						+ "\n\nActivation Code: "+activationCode);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}


}
}
