package org.example.servletfullstack.services;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Authenticator;
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
    private static final Properties props = new Properties();

    /**
     * A private constructor for EmailService to acheive singleton status
     */
    private EmailService() {}

    // Set up the props in a static block to avoid reallocation in case of class extension
    static {
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
        if (emailService == null) { emailService = new EmailService(); }

        return emailService;
    }

    /**
     * Sends an email to the configured mailing address
     * @param sender who's sending the email
     * @param subject the subject line
     * @param text the content of the email
     * @return whether the email was sent successfully
     */
    public boolean sendEmail(String sender, String subject, String text) {
        final String PASSWORD = MailConfig.PASSWORD;
        final String FROM = MailConfig.FROM;            // We don't send the email from the senders address but instead one of ours
        final String TO = MailConfig.TO;

        // Format the subject into Sender: Subject
        subject = String.format("%s: %s", sender, subject);

        try {
            // Create an SMTP session for sending the message over
            Session session = createSession(FROM, PASSWORD);

            // Create and send the message
            Transport.send(createMessage(FROM, session, TO, subject, text));

        } catch (MessagingException e) {
            System.out.println("Failed to send email");
            return false;
        }

        // true indicates the message was successfully sent
        return true;
    }

    /**
     * Creates a new session based on the props and who's sending the email
     * @param from The email address of who's sending the email
     * @param password The app password for the sender
     * @return Session object to send message over
     */
    private Session createSession(String from, String password) {
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    /**
     * Creates an email message object
     * @param from Who's sending the message
     * @param to Who to send the email to
     * @param subject The subject of the email
     * @param text The body of the email
     * @return MimeMessage ready for sending
     * @throws MessagingException If an error occurs when trying to build the message
     */
    private MimeMessage createMessage(String from, Session session, String to, String subject, String text) throws MessagingException {
        // Create the blank message
        MimeMessage message = new MimeMessage(session);

        // Set up the email message
        message.setFrom(new InternetAddress(from));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(text, "text/plain");

        return message;
    }
}
