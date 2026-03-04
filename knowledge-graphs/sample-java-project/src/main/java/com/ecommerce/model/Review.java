package com.ecommerce.model;

/**
 * A product review written by a user.
 */
public class Review extends BaseEntity {

    private User author;
    private Product product;
    private int rating;
    private String comment;

    public Review(User author, Product product, int rating, String comment) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.author = author;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
    }

    public User getAuthor() { return author; }
    public Product getProduct() { return product; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}
