package org.example.servletfullstack.repositories;

// contains sensitive database configuration details
// This is why it is not included in the GitHub repository
import org.example.resources.DatabaseConfig;

import org.example.servletfullstack.repositories.objects.Product;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    /**
     * The singleton instance of the ProductRepo
     */
    private static ProductRepo productRepo;

    /**
     * The connection to the database
     */
    private Connection conn;

    /**
     * A list of all the prodcuts in the database
     */
    private List<Product> products;

    /**
     * Tracks the last time the database was queried
     */
    private long lastUpdate;

    /**
     * How much time must pass before we will update the memoized values
     */
    private final long DURATIONTILLUPDATE = 600_000_000_000L;  // 10 Minutes

    /**
     * A private constructor for ProductRepo to acheive singleton status, initializes the database connection
     */
    private ProductRepo() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(DatabaseConfig.getURL(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find PostgreSQL JDBC driver: " + e.getMessage());
        }
    }

    /**
     * Returns the reference to a ProductRepo object
     * @return ProductRepo object
     */
    synchronized public static ProductRepo getProductRepo() {
        if (productRepo == null) productRepo = new ProductRepo();

        return productRepo;
    }

    /**
     * Queries the database for all products and returns a list of Product objects
     * @return list of product objects
     */
    public List<Product> getAllProducts() {
        // If we have already fetched the products from the database we just return the memoized list
        // We will pull from the database again based on how much time has passed just in case the database updates
        // during uptime
        if (this.products == null || System.nanoTime() - this.lastUpdate >= DURATIONTILLUPDATE) {
            fetchProducts();
        }

        return this.products;
    }

    /**
     * Fetches all products from the database and assigns them to the products attribute
     */
    private void fetchProducts() {
        this.products = new ArrayList<>();

        try {
            // Prepare and execute a statement to query the database for all products
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");

            // Go through every row of data and create a product from it to add to the array
            while (rs.next()) {
                this.products.add(
                        new Product(
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getString("product_desc"),
                                rs.getDouble("product_price")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage());
        }

        // Track the last time we updated our memoized data
        this.lastUpdate = System.nanoTime();
    }

    /**
     * Shuts down the JDBC connection
     */
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection: " + e.getMessage());
        }
    }
}
