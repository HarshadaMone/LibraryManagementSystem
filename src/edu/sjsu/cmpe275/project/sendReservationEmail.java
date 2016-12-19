package edu.sjsu.cmpe275.project;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.sjsu.cmpe275.project.model.Book;

public class sendReservationEmail {
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
	public static void sendEmail(String to,Book book) {
		
		
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");
    	Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 3);
	
		String body="Dear User,"
				+ "\n\n The book :"+book.getTitle()
				+"\n for which you were on waitlist is been reserved for you,"
				+"\nit will be reserved for 3 days i.e till"+cal.getTime();
		
		Session session = Session.getDefaultInstance(props);
    	Transport transport = null;
    	MimeMessage msg = new MimeMessage(session);
    	try{
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
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
	
}
