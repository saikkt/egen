package com.egen.order.service;

import com.egen.order.model.domain.Order;
import com.egen.order.model.domain.Payment;
import com.egen.order.model.dto.OrderStatusDto;

import java.util.List;

public interface OrderService {

    OrderStatusDto createOrder(Order order);
    Order getOrderById(String orderId);
    Void deleteOrderById(String orderId);
    Order updateOrderPayment(String orderId, Payment payment);
    List<Order> getOrderHistory(String customerId);
}
