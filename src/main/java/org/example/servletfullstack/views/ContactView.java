package org.example.servletfullstack.views;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The view for the /contact webpage
 */
@WebServlet(name="ContactView", urlPatterns = "/contact")
public class ContactView extends HttpServlet {
    /**
     * The cached webpage
     */
    private String page;

    public void init() {
        log("Contact View Initialized");
    }

    /**
     * Returns the /contact webpage
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if servlet exception occured
     * @throws IOException if input or output exception occured
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if (this.page != null) {
            response.getWriter().println(this.page);
        } else {
            this.page = ThymeleafFacade.requestPage("contact", request, response);
            response.getWriter().println(this.page);
        }
    }

    public void destroy() {
        log("Contact View Destroyed");
    }
}
