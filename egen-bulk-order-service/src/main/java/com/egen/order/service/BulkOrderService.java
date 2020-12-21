package com.egen.order.service;

import com.egen.order.model.domain.Order;

import java.util.List;

public interface BulkOrderService {

    void createBulkOrders(List<Order> orders);
    void deleteOrderById(long orderId);
    void deleteAllInBatch(List<Order> orders);
}
