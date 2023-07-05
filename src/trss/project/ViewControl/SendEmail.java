package trss.project.ViewControl;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static void main(String[] args) throws MessagingException {
        // Set up your email credentials
        String senderEmail = "khaledain10.101@gmail.com";
        String senderPassword = "znfuscwcmowuybqm";
// jjkiovlabrrdaedq
        // Define the email message
        String sent_from = senderEmail;
        String[] to = {"3810175@upm.edu.sa"};
        String subject = "Greating From Khaled Aineih";
        String body = "Please pray for us , we have presntation for the final project ";

        // Create a Properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session object with Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create a Message object
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sent_from));

        // Convert the array of email addresses to an array of InternetAddress objects
        InternetAddress[] recipientAddresses = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            recipientAddresses[i] = new InternetAddress(to[i]);
        }

        // Set the recipients of the email
        message.setRecipients(Message.RecipientType.TO, recipientAddresses);
        message.setSubject(subject);
        message.setText(body);

        // Send the message
        Transport.send(message);

        System.out.println("Email sent successfully.");
    }
}