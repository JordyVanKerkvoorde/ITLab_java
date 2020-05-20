package domain.model;

import domain.model.user.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mail implements Runnable {

    private final String from = "itlab29@gmail.com";
    private final String host = "smtp.gmail.com";
    private final String fromPasswd = "Wachtwoord1234";

    // Get system properties
    private Properties properties = System.getProperties();
    private String subject;
    private String body;
    private List<User> recipients;


    public Mail() {
        recipients = new ArrayList<>();
    }

    public void setupMail(String subject, String body, List<User> recipients){
        this.subject = subject;
        this.body = body;
        this.recipients = recipients;
    }

    @Override
    public void run() {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, fromPasswd);

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            for (User r: recipients) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(r.getEmail()));
            }

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}