package edu.sjsu.cmpe275.project;

import java.util.Calendar;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import edu.sjsu.cmpe275.project.model.Book;
import edu.sjsu.cmpe275.project.model.Checkout;
import edu.sjsu.cmpe275.project.model.User;

public class AmazonSESSample {
	static final String FROM = "ajaymodi12@gmail.com";   // Replace with your "From" address. This address must be verified.
      // Replace with a "To" address. If your account is still in the 
                                                       // sandbox, this address must be verified.
    
    static final String BODY = "This email was sent through the Amazon SES SMTP interface by using Java.";
    static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
    
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = "AKIAJNEBULFC77GZDZNA";  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = "AmTGOFFOsJqo2fthN66DQPPH6vpV6jVcmR61Z+wx2SCE";  // Replace with your SMTP password.
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";    
    
    // The port you will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 25;
    
    public static void sendEmail(String to,Book book){
    	Calendar cal=Calendar.getInstance();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE, 30);
    	final String TO = to;
    	final String body="Dear User,"
				+ "\n\n You have done a book checkout"
				+ "\n\nBook Name: "+book.getTitle()
				+ "\nBook Author: "+book.getAuthor()
				+ "\n\ncheckout Date: "+cal.getTime()
				+ "\n\nReturn Date: "+cal1.getTime();
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");
    	
    	Session session = Session.getDefaultInstance(props);
    	Transport transport = null;
    	MimeMessage msg = new MimeMessage(session);
    	try{
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject("Library Notification");
        msg.setContent(body,"text/plain");
    	}catch (MessagingException e) {
    		throw new RuntimeException(e);
    		// TODO: handle exception
		}
        // Create a transport.        
        try {
			 transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }     
   
    }
    
	public static void sendEmail(String to, String from,int activationCode) {

	    String TO = to;
    	final String body="Dear User,"
				+ "\n\n Following is your authentication code!"
					+ "\n\nActivation Code: "+activationCode;
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

       	Session session = Session.getDefaultInstance(props);
    	Transport transport = null;
    	MimeMessage msg = new MimeMessage(session);
    	try{
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject("Library Notification");
        msg.setContent(body,"text/plain");
    	}catch (MessagingException e) {
    		throw new RuntimeException(e);
    		// TODO: handle exception
		}
        // Create a transport.        
        try {
			 transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }     


}
	public static void checkoutDateUpdated(User user,Book book,Checkout checkout) {
		 String TO = user.getEmail();
	    final String body="Dear User,"
				+ "\n\n Checkout Date Updated"
				+ "\n\nBook Name: "+book.getTitle()
				+ "\nBook Author: "+book.getAuthor()
				+ "\n\nUpdated Return Date: "+checkout.getReturnDate();

		
		Calendar cal=Calendar.getInstance();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE, 30);
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

       	Session session = Session.getDefaultInstance(props);
    	Transport transport = null;
    	MimeMessage msg = new MimeMessage(session);
    	try{
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject("Library Notification");
        msg.setContent(body,"text/plain");
    	}catch (MessagingException e) {
    		throw new RuntimeException(e);
    		// TODO: handle exception
		}
        // Create a transport.        
        try {
			 transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        } 
	}
	
	
public static void checkoutFine(User user,Book book,Checkout checkout) {
	 String TO = user.getEmail();
	    final String body="Dear User,"
				+ "\n\n Fine on checkout book updated"
				+ "\n\nBook Name: "+book.getTitle()
				+ "\nBook Author: "+book.getAuthor()
				+ "\n\nUpdated Fine: "+checkout.getFine()+" $";
		
		Calendar cal=Calendar.getInstance();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE, 30);
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

       	Session session = Session.getDefaultInstance(props);
    	Transport transport = null;
    	MimeMessage msg = new MimeMessage(session);
    	try{
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject("Fine on checkout book updated");
        msg.setContent(body,"text/plain");
    	}catch (MessagingException e) {
    		throw new RuntimeException(e);
    		// TODO: handle exception
		}
        // Create a transport.        
        try {
			 transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        } 
}

public static void returnReminder(User user,Book book,Checkout checkout,int diffInDays) {
	
	String TO = user.getEmail();
    final String body="Dear User,"
			+ "\n\n Return Book within "+diffInDays+" Days"
			+ "\n\nBook Name: "+book.getTitle()
			+ "\nBook Author: "+book.getAuthor();
	
	Calendar cal=Calendar.getInstance();
	Calendar cal1=Calendar.getInstance();
	cal1.add(Calendar.DATE, 30);
	diffInDays=diffInDays*(-1);
	Properties props = System.getProperties();
	props.put("mail.transport.protocol", "smtps");
	props.put("mail.smtp.port", PORT); 
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.starttls.required", "true");

	Session session = Session.getDefaultInstance(props);
	Transport transport = null;
	MimeMessage msg = new MimeMessage(session);
	try{
    msg.setFrom(new InternetAddress(FROM));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
    msg.setSubject("Return Book within "+diffInDays+" Days");
    msg.setContent(body,"text/plain");
	}catch (MessagingException e) {
		throw new RuntimeException(e);
		// TODO: handle exception
	}
    // Create a transport.        
    try {
		 transport = session.getTransport();
	} catch (NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try
    {
        System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
        
        // Connect to Amazon SES using the SMTP username and password you specified above.
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
    	
        // Send the email.
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Email sent!");
    }
    catch (Exception ex) {
        System.out.println("The email was not sent.");
        System.out.println("Error message: " + ex.getMessage());
    }
    finally
    {
        // Close and terminate the connection.
        try {
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        	
    } 
}
}
