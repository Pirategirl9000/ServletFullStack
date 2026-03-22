package org.example.servletfullstack.services;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.example.resources.MailConfig;

import java.util.Properties;

/**
 * Handles the operations passed to it by the Controller layer. Responsible for sending emails to a set address
 */
public class EmailService {
    /**
     * The singleton instance of the EmailService
     */
    private static EmailService emailService;

    /**
     * The properties for the email session
     */
    private final Properties props = new Properties();

    /**
     * A private constructor for EmailService to acheive singleton status
     */
    private EmailService() {
        props.put("mail.smtp.host", MailConfig.HOST);   // Which service to use
        props.put("mail.smtp.auth", "true");            // Use authentication
        props.put("mail.smtp.port", "465");             // SSL port
        props.put("mail.smtp.ssl.enable", "true");      // Enable SSL encryption
    }

    /**
     * Returns the reference to a EmailService object
     * @return EmailService object
     */
    synchronized public static EmailService getEmailService() {
        if (emailService == null) emailService = new EmailService();

        return emailService;
    }

    synchronized public boolean sendEmail(String sender, String subject, String text) {
        final String PASSWORD = MailConfig.PASSWORD;
        final String FROM = MailConfig.FROM;            // We don't send the email from the senders address but instead one of ours
        final String TO = MailConfig.TO;

        // Format the subject into Sender: Subject
        subject = String.format("%s: %s", sender, subject);

        // Set up the session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            // Set up the email
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(TO));
            message.setSubject(subject);
            message.setText(text, "UTF-8");

            // Send the message
            Transport.send(message);

            return true;
        } catch (Exception e) {
            System.out.println("Failed to send message: " + e.getMessage());
            return false;
        }
    }
}
