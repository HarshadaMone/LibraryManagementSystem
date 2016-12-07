package edu.sjsu.cmpe275.project;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.sjsu.cmpe275.project.model.Book;

public class SendCheckoutEmail {
	
	public static void sendEmail(String to,Book book) {
	
		final String username = "";
		final String password = "";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Calendar cal=Calendar.getInstance();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE, 30);
		
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
				+ "\n\n You have done a book checkout"
					+ "\n\nBook Name: "+book.getTitle()
					+ "\nBook Author: "+book.getAuthor()
					+ "\n\ncheckout Date: "+cal.getTime()
					+ "\n\nReturn Date: "+cal1.getTime());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
