package org.example.servletfullstack.views;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="HomeView", urlPatterns = {"/home", "/index"})
public class HomeView extends HttpServlet {
    /**
     * The cached webpage
     */
    private String page;

    /**
     * Returns the /home webpage
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
            this.page = ThymeleafFacade.requestPage("home", request, response);
            response.getWriter().println(this.page);
        }
    }
}
