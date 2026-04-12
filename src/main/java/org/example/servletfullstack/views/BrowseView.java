package org.example.servletfullstack.views;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletfullstack.models.Product;
import org.example.servletfullstack.services.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * The view for the /browse webpage
 */
@WebServlet(name="BrowseView", urlPatterns = "/browse")
public class BrowseView extends HttpServlet {
    /**
     * Handles the operations related to requests the controller recieves
     */
    private ProductService productService;

    /**
     * Code that runs when the Servlet is initialilized
     */
    public void init() {
        log("Browse View Initialized");
        this.productService = ProductService.getProductService();
    }

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
        Map<String, Object> variables = new HashMap<>();

        Product[] products = productService.getProducts();

        if (products == null || products.length == 0) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Could not find products");
            log("Internal Server Error: Failed to fetch products from database");
            return;
        }


        variables.put("products", products);


        out.println(ThymeleafFacade.requestPage("browse", request, response, variables));
    }

    public void destroy() {
        log("Browse View Destroyed");
    }

}
