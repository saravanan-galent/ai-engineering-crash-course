package com.ecommerce.model;

import java.math.BigDecimal;

/**
 * A single line item in an order, linking a product to a quantity.
 */
public class OrderItem {

    private Product product;
    private int quantity;
    private BigDecimal priceAtPurchase;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = product.getPrice();
    }

    public BigDecimal getSubtotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }
}
