package org.example.servletfullstack.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletfullstack.services.EmailService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles HTTP requests sent to the server and delegates the work to the service layer
 */
@WebServlet(name = "productController", value = "/servletfullstack/api/email")
public class EmailController extends HttpServlet {
    /**
     * Handles the operations related to requests the controller recieves
     */
    private EmailService emailService;

    /**
     * Code that runs when the Servlet is initialilized
     */
    public void init() {
        this.emailService = EmailService.getEmailService();
    }

    /**
     * Responds to POST requests by attempting to send the email
     * @param request the request sent
     * @param response the response to send
     * @throws ServletException if an error with the servlet occurs
     * @throws IOException if an error occurs during IO
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String sender = request.getParameter("from");
        final String subject = request.getParameter("subject");
        final String body = request.getParameter("body");

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        final boolean success = emailService.sendEmail(sender, subject, body);

        if (success) {
            out.println("Email sent!");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    /**
     * Code that runs when Servlet is being terminated
     */
    public void destroy() {
    }


}
