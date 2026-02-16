package org.example.servletfullstack.services;

/**
 * Handles the operations passed to it by the Controller layer. Is a singleton class to prevent database access issues
 */
public class ProductService {
    /**
     * The singleton instance of the ProductService
     */
    private static ProductService productService;

    /**
     * A private constructor for ProductService to acheive singleton status
     */
    private ProductService() {}

    /**
     * Returns the reference to a ProductService object
     * @return ProductService object
     */
    public static ProductService getProductService() {
        if (productService == null) productService = new ProductService();

        return productService;
    }

    /**
     * Returns all products in a JSON formatted string
     * @return JSON formatted string
     */
    public String getProducts() {

    }

    /**
     * Shuts down the service class
     */
    public void destroy() {

    }
}
