package org.example.servletfullstack.repositories.objects;

/**
 * Data carrier class for product items retrieved from the database
 * @param id the primary key id of this product
 * @param name the name of the product
 * @param description the description for the product
 * @param price the price of the product
 * @param categories the categories this product falls under
 */
public record Product(int id, String name, String description, double price, String[] categories){}