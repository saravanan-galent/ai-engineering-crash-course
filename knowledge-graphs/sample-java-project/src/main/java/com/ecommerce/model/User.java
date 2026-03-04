package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a registered user who can place orders and write reviews.
 */
public class User extends BaseEntity {

    private String email;
    private String username;
    private String passwordHash;
    private UserRole role;
    private List<Order> orders;
    private ShoppingCart cart;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
        this.role = UserRole.CUSTOMER;
        this.orders = new ArrayList<>();
        this.cart = new ShoppingCart(this);
    }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public Order placeOrder() {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }
        Order order = cart.checkout();
        orders.add(order);
        return order;
    }

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public List<Order> getOrders() { return orders; }
    public ShoppingCart getCart() { return cart; }
}
