package com.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a product in the e-commerce catalog.
 * Products belong to categories and can have multiple reviews.
 */
public class Product extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private Category category;
    private List<Review> reviews;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = 0;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public BigDecimal getDiscountedPrice(double discountPercent) {
        BigDecimal discount = price.multiply(BigDecimal.valueOf(discountPercent / 100));
        return price.subtract(discount);
    }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) return 0.0;
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int qty) { this.stockQuantity = qty; }
    public Category getCategory() { return category; }
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
