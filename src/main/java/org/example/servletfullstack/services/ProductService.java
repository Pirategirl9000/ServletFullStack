package org.example.servletfullstack.services;

import com.google.gson.Gson;
import org.example.servletfullstack.repositories.ProductRepo;

/**
 * Handles the operations passed to it by the Controller layer. Is a singleton class to prevent database access issues
 */
public class ProductService {
    /**
     * The singleton instance of the ProductService
     */
    private static ProductService productService;

    /**
     * Reference to the Product Repository which handles the database
     */
    private ProductRepo productRepo;

    /**
     * Parses objects to JSON formatted string
     */
    private Gson JSONParser = new Gson();

    /**
     * A private constructor for ProductService to acheive singleton status
     */
    private ProductService() {
        this.productRepo = ProductRepo.getProductRepo();
    }

    /**
     * Returns the reference to a ProductService object
     * @return ProductService object
     */
    synchronized public static ProductService getProductService() {
        if (productService == null) productService = new ProductService();

        return productService;
    }

    /**
     * Returns all products in a JSON formatted string
     * @return JSON formatted string
     */
    public String getProducts() {
        return JSONParser.toJson(productRepo.getAllProducts());
    }

    /**
     * Shuts down the service class
     */
    public void destroy() {
        productRepo.destroy();
    }
}
