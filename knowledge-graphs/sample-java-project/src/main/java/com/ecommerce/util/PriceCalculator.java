package com.ecommerce.util;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for pricing, tax, and discount calculations.
 */
public class PriceCalculator {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.08");
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("50.00");
    private static final BigDecimal SHIPPING_FEE = new BigDecimal("5.99");

    public static BigDecimal calculateTax(BigDecimal subtotal) {
        return subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateShipping(BigDecimal subtotal) {
        return subtotal.compareTo(FREE_SHIPPING_THRESHOLD) >= 0
                ? BigDecimal.ZERO
                : SHIPPING_FEE;
    }

    public static BigDecimal calculateOrderTotal(Order order) {
        BigDecimal subtotal = order.getTotalAmount();
        BigDecimal tax = calculateTax(subtotal);
        BigDecimal shipping = calculateShipping(subtotal);
        return subtotal.add(tax).add(shipping);
    }

    public static BigDecimal applyBulkDiscount(OrderItem item) {
        if (item.getQuantity() >= 10) {
            return item.getSubtotal().multiply(new BigDecimal("0.90"));
        } else if (item.getQuantity() >= 5) {
            return item.getSubtotal().multiply(new BigDecimal("0.95"));
        }
        return item.getSubtotal();
    }
}
