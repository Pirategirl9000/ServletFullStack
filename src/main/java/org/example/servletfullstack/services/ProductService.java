package org.example.servletfullstack.services;

import org.example.servletfullstack.repositories.ProductRepo;
import org.example.servletfullstack.models.Product;

/**
 * Handles the operations passed to it by the Controller layer. Is a singleton class to prevent database access issues
 */
public class ProductService {
    /**
     * The singleton instance of the ProductService
     */
    private static volatile ProductService productService;

    /**
     * Reference to the Product Repository which handles the database
     */
    private ProductRepo productRepo;

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
    public static ProductService getProductService() {
        synchronized (ProductService.class) {
            if (productService == null) productService = new ProductService();
        }

        return productService;
    }

    /**
     * Returns all products in a Product[]
     * @return Array of Product objects
     */
    public Product[] getProducts() {
        return productRepo.getAllProducts().toArray(Product[]::new);
    }
}
