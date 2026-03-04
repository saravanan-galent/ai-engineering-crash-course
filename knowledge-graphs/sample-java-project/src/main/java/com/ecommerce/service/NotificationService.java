package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.model.Product;

/**
 * Sends notifications to users via email and push notifications.
 */
public class NotificationService {

    public void sendOrderConfirmation(Order order) {
        String email = order.getCustomer().getEmail();
        send(email, "Order Confirmed", "Your order #" + order.getId() + " has been confirmed.");
    }

    public void sendCancellationNotice(Order order) {
        String email = order.getCustomer().getEmail();
        send(email, "Order Cancelled", "Your order #" + order.getId() + " has been cancelled.");
    }

    public void notifyRestock(Product product) {
        // Would query subscribers interested in this product
        System.out.println("Restock notification: " + product.getName() + " is back in stock!");
    }

    private void send(String to, String subject, String body) {
        // In production, this delegates to an email service like SendGrid or SES
        System.out.println("EMAIL to " + to + ": [" + subject + "] " + body);
    }
}
