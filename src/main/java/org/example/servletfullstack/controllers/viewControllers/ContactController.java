package org.example.servletfullstack.controllers.viewControllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="ContactController", urlPatterns = "/contact")
public class ContactController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(req, res);
    }
}
