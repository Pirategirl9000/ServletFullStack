package org.example.servletfullstack.controllers;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletfullstack.controllers.objects.EmailRequest;
import org.example.servletfullstack.services.EmailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles HTTP requests sent to the server and delegates the work to the service layer
 */
@WebServlet(name = "emailController", value = "/api/email")
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
        BufferedReader reader = request.getReader();

        Gson gson = new Gson();

        EmailRequest requestBody = gson.fromJson(reader, EmailRequest.class);

        // Grab the values of the body so we can send our email
        final String sender = requestBody.from();
        final String subject = requestBody.subject();
        final String body = requestBody.body();

        // If any data was missing we send a bad request error
        if (sender == null || subject == null || body == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
            return;
        }

        final boolean success = emailService.sendEmail(sender, subject, body);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

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
