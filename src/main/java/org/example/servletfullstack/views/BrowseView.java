package org.example.servletfullstack.views;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * The view for the /browse webpage
 */
@WebServlet(name="BrowseView", urlPatterns = "/browse")
public class BrowseView extends HttpServlet {

    /**
     * Returns the /browse webpage
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if servlet exception occured
     * @throws IOException if input or output exception occured
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //TODO: Build this webpage with the mappings

        out.println(ThymeleafFacade.requestPage("browse", request, response));
    }

}
