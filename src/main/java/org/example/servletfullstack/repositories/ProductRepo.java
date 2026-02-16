package org.example.servletfullstack.repositories;


public class ProductRepo {
    /**
     * The singleton instance of the ProductRepo
     */
    private static ProductRepo productRepo;

    /**
     * A private constructor for ProductRepo to acheive singleton status
     */
    private ProductRepo() {}

    /**
     * Returns the reference to a ProductRepo object
     * @return productRepo object
     */
    synchronized public static ProductRepo getProductRepo() {
        if (productRepo == null) productRepo = new ProductRepo();

        return productRepo;
    }

    /**
     * Shuts down the service class
     */
    public void destroy() {

    }
}
