package com.egen.order.mapper;


import com.egen.order.model.client.OrderEvent;
import com.egen.order.model.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderEventMapper {

    private OrderEventMapper() {
    }

    public static List<OrderEvent> toOrderEvent(List<Order> orders){
        List<OrderEvent> orderEvents = new ArrayList<>();
        orders.forEach(order -> {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder_id(order.getOrderId());
            orderEvent.setOrderStatus(order.getOrderStatus());
            orderEvent.setOrdershippingaddressline1(order.getOrderShippingAddressline1());
            orderEvent.setOrderShippingAddressline2(order.getOrderShippingAddressline2());
            orderEvent.setOrderShippingCharges(order.getOrderShippingCharges());
            orderEvent.setOrderShippingCity(order.getOrderShippingCity());
            orderEvent.setOrderShippingState(order.getOrderShippingState());
            orderEvent.setOrderShippingZip(order.getOrderShippingZip());
            orderEvents.add(orderEvent);
        });

        return orderEvents;
    }
}
