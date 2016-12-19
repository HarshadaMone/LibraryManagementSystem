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
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;

public class SendCheckoutEmail {
	
	public static void sendEmail(String to,Book book) {
	
		final String username = "ajaymodi12@gmail.com";
		final String password = "pradeep";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");		
		 props.put("mail.smtp.port", "465");
		//props.put("mail.smtp.port", "587");
		
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
	
	public static void checkoutDateUpdated(User user,Book book,Checkout checkout) {
		
		final String username = "ajaymodi12@gmail.com";
		final String password = "pradeep";
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
				InternetAddress.parse(user.getEmail()));
			message.setSubject("Return Date Updated");
			message.setText("Dear User,"
				+ "\n\n Checkout Date Updated"
					+ "\n\nBook Name: "+book.getTitle()
					+ "\nBook Author: "+book.getAuthor()
					+ "\n\nUpdated Return Date: "+checkout.getReturnDate());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
public static void checkoutFine(User user,Book book,Checkout checkout) {
		
		final String username = "ajaymodi12@gmail.com";
		final String password = "pradeep";
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
				InternetAddress.parse(user.getEmail()));
			message.setSubject("Fine on checkout book updated");
			message.setText("Dear User,"
				+ "\n\n Fine on checkout book updated"
					+ "\n\nBook Name: "+book.getTitle()
					+ "\nBook Author: "+book.getAuthor()
					+ "\n\nUpdated Fine: "+checkout.getFine()+" $");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
public static void returnReminder(User user,Book book,Checkout checkout,int diffInDays) {
	
	final String username = "ajaymodi12@gmail.com";
	final String password = "pradeep";
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
		diffInDays=diffInDays*(-1);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(user.getEmail()));
		message.setSubject("Return Book within "+diffInDays+" Days");
		message.setText("Dear User,"
			+ "\n\n Return Book within "+diffInDays+" Days"
				+ "\n\nBook Name: "+book.getTitle()
				+ "\nBook Author: "+book.getAuthor());

		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
	
}

}
