package edu.sjsu.cmpe275.project;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.sjsu.cmpe275.project.model.Book;

public class sendReservationEmail {
	
	public static void sendEmail(String to,Book book) {
		
		final String username = "ajaymodi12@gmail.com";
		final String password = "pradeep";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 3);
	
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
			message.setSubject("CheckOut Notification");
			message.setText("Dear User,"
					+ "\n\n The book :"+book.getTitle()
					+"\n for which you were on waitlist is been reserved for you,"
					+"\nit will be reserved for 3 days i.e till"+cal.getTime());
			Transport.send(message);

			System.out.println("Done");
	}catch (MessagingException e) {
		throw new RuntimeException(e);
	}

}
	
}
