package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages order lifecycle: creation, payment, shipping, cancellation.
 * Coordinates between PaymentService and NotificationService.
 */
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, PaymentService paymentService,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public Order createOrder(User user) {
        Order order = user.placeOrder();
        orderRepository.save(order);
        notificationService.sendOrderConfirmation(order);
        return order;
    }

    public void processPayment(Long orderId, PaymentMethod method) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        PaymentInfo payment = paymentService.charge(order.getTotalAmount(), method);
        if (payment.isSuccessful()) {
            order.confirm(payment);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Payment failed for order: " + orderId);
        }
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.cancel();
        orderRepository.save(order);
        notificationService.sendCancellationNotice(order);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
    }
}
