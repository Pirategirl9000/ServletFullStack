package org.example.servletfullstack.repositories;

// contains sensitive database configuration details
// This is why it is not included in the GitHub repository
import org.example.resources.DatabaseConfig;

import com.google.gson.Gson;
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
     * A list of all the prodcuts in the database
     */
    private volatile List<Product> products;

    /**
     * Tracks the last time the database was queried
     */
    private volatile long lastUpdate;

    /**
     * How much time must pass before we will update the memoized values
     */
    private final long DURATIONTILLUPDATE = 600_000_000_000L;  // 10 Minutes

    /**
     * A private constructor for ProductRepo to acheive singleton status, initializes the database connection
     */
    private ProductRepo() {
        // Set up the driver for PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load PostgreSQL driver", e);
        }
    }

    /**
     * Returns a JDBC Connection to the database
     * @return Connection object tied to the database
     */
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(DatabaseConfig.getURL(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection to database: " + e.getMessage());
        }
    }

    /**
     * Returns the reference to a ProductRepo object
     * @return ProductRepo object
     */
    synchronized public static ProductRepo getProductRepo() {
        if (productRepo == null) { productRepo = new ProductRepo(); }

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
            this.products = fetchProducts();
        }

        return this.products;
    }

    /**
     * Fetches all products from the database and assigns them to the products attribute
     */
    synchronized private List<Product> fetchProducts() {
        final String query =
                "select p.*, JSON_AGG(c.category_name) AS categories from products p " +
                "INNER JOIN categories c ON p.product_id = c.product_id " +
                "GROUP BY p.product_id;";

        final List<Product> newProducts = new ArrayList<>();

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {

            // Map our result to Product objects
            while (rs.next()) {
                newProducts.add(mapProduct(rs));
            }


        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage());
        }

        // Update when we last queried the database
        this.lastUpdate = System.nanoTime();
        return newProducts;
    }

    /**
     * Maps the database row to a Product object
     * @param rs The result set of the query
     * @return A product object matching the query row
     * @throws SQLException if any column labels are invalid
     */
    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_desc"),
                rs.getDouble("product_price"),
                new Gson().fromJson(rs.getString("categories"), String[].class)
        );
    }
}