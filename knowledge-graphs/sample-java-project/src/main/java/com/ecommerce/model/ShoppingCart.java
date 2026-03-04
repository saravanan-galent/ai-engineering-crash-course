package com.ecommerce.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A user's shopping cart that accumulates items before checkout.
 */
public class ShoppingCart {

    private User owner;
    private List<OrderItem> items;

    public ShoppingCart(User owner) {
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (!product.isInStock()) {
            throw new IllegalStateException("Product is out of stock: " + product.getName());
        }
        items.add(new OrderItem(product, quantity));
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Order checkout() {
        Order order = new Order(owner, new ArrayList<>(items));
        items.clear();
        return order;
    }

    public List<OrderItem> getItems() { return items; }
}
