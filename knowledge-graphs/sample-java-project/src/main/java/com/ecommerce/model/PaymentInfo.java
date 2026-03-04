package com.ecommerce.model;

import java.math.BigDecimal;

/**
 * Holds payment details for an order.
 */
public class PaymentInfo {

    private String transactionId;
    private PaymentMethod method;
    private BigDecimal amount;
    private boolean successful;

    public PaymentInfo(String transactionId, PaymentMethod method, BigDecimal amount) {
        this.transactionId = transactionId;
        this.method = method;
        this.amount = amount;
        this.successful = false;
    }

    public void markSuccessful() { this.successful = true; }
    public boolean isSuccessful() { return successful; }
    public String getTransactionId() { return transactionId; }
    public PaymentMethod getMethod() { return method; }
    public BigDecimal getAmount() { return amount; }
}
