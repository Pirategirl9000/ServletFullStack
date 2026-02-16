package org.example.servletfullstack.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletfullstack.services.ProductService;

import java.io.IOException;

/**
 * Handles HTTP requests sent to the server and delegates the work to the service layer
 */
@WebServlet(name = "productController", value = "/product-controller")
public class ProductController extends HttpServlet {
    /**
     * Handles the operations related to requests the controller recieves
     */
    private ProductService productService;

    /**
     * Code that runs when the Servlet is initialilized
     */
    public void init() {
        this.productService = ProductService.getProductService();
    }

    /**
     * Handles the GET requests
     * @param request the request sent
     * @param response the response to send
     * @throws ServletException if an error with the servlet occurs
     * @throws IOException if an error occurs during IO
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    /**
     * Handles the POST requests
     * @param request the request sent
     * @param response the response to send
     * @throws ServletException if an error with the servlet occurs
     * @throws IOException if an error occurs during IO
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    /**
     * Code that runs when Servlet is being terminated
     */
    public void destroy() {
        productService.destroy();

    }


}
