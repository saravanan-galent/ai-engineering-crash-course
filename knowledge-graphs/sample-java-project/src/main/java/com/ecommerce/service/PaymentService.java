package com.ecommerce.service;

import com.ecommerce.model.PaymentInfo;
import com.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Handles payment processing through external payment gateways.
 */
public class PaymentService {

    public PaymentInfo charge(BigDecimal amount, PaymentMethod method) {
        String transactionId = UUID.randomUUID().toString();
        PaymentInfo payment = new PaymentInfo(transactionId, method, amount);
        boolean success = processWithGateway(amount, method);
        if (success) {
            payment.markSuccessful();
        }
        return payment;
    }

    public void refund(String transactionId, BigDecimal amount) {
        // In a real system this would call the payment gateway's refund API
        System.out.println("Refund issued: " + transactionId + " amount: " + amount);
    }

    private boolean processWithGateway(BigDecimal amount, PaymentMethod method) {
        // Simulated gateway call — in production this hits Stripe/PayPal/etc.
        return amount.compareTo(BigDecimal.valueOf(10000)) < 0;
    }
}
