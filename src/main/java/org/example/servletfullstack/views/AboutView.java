package org.example.servletfullstack.views;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The view for the /about webpage
 */
@WebServlet(name="AboutView", urlPatterns = "/about")
public class AboutView extends HttpServlet {
    /**
     * The cached webpage
     */
    private String page;

    public void init() {
        log("About View Initialized");
    }

    /**
     * Returns the /about webpage
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
            this.page = ThymeleafFacade.requestPage("about", request, response);
            response.getWriter().println(this.page);
        }
    }

    public void destroy() {
        log("About View Destroyed");
    }
}
