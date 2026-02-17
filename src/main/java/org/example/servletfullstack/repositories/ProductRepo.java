package org.example.servletfullstack.repositories;

// contains sensitive database configuration details
// This is why it is not included in the GitHub repository
import org.example.resources.DatabaseConfig;
import org.example.servletfullstack.repositories.objects.Product;

import java.sql.*;
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
     * A private constructor for ProductRepo to acheive singleton status, initializes the database connection
     */
    private ProductRepo() {
        try {
            this.conn = DriverManager.getConnection(DatabaseConfig.getURL(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection to database: " + e.getMessage());
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

    public List<Product> getProducts() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");

            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                products.add(
                        new Product(
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getString("product_desc"),
                                rs.getDouble("product_price")
                        )
                );
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage());
        }
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
