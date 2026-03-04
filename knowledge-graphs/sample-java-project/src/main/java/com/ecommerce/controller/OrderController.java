package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderStatus;
import com.ecommerce.model.PaymentMethod;
import com.ecommerce.model.User;
import com.ecommerce.service.OrderService;

import java.util.List;

/**
 * REST controller for order management endpoints.
 */
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order checkout(User user) {
        return orderService.createOrder(user);
    }

    public void pay(Long orderId, PaymentMethod method) {
        orderService.processPayment(orderId, method);
    }

    public void cancel(Long orderId) {
        orderService.cancelOrder(orderId);
    }

    public List<Order> getByStatus(OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }
}
