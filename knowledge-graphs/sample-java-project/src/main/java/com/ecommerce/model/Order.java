package com.ecommerce.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a customer order containing one or more order items.
 * Tracks order status through its lifecycle.
 */
public class Order extends BaseEntity {

    private User customer;
    private List<OrderItem> items;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private PaymentInfo paymentInfo;
    private String shippingAddress;

    public Order(User customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.totalAmount = calculateTotal();
    }

    private BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirm(PaymentInfo payment) {
        this.paymentInfo = payment;
        this.status = OrderStatus.CONFIRMED;
    }

    public void ship(String trackingNumber) {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be confirmed before shipping");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void cancel() {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel shipped/delivered order");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public User getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}
