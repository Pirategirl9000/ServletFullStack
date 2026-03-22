package org.example.servletfullstack.services;

import org.example.servletfullstack.repositories.ProductRepo;

/**
 * Handles the operations passed to it by the Controller layer. Responsible for sending emails to a set address
 */
public class EmailService {
    /**
     * The singleton instance of the EmailService
     */
    private static EmailService emailService;

    /**
     * A private constructor for EmailService to acheive singleton status
     */
    private EmailService() {

    }

    /**
     * Returns the reference to a EmailService object
     * @return EmailService object
     */
    synchronized public static EmailService getEmailService() {
        if (emailService == null) emailService = new EmailService();

        return emailService;
    }
}
