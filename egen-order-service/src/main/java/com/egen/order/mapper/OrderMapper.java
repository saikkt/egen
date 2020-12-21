package com.egen.order.mapper;


import com.egen.order.model.client.OrderPlacedDto;
import com.egen.order.model.domain.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderPlacedDto toOrderPlacedDto(Order order){
            OrderPlacedDto orderPlacedDto = new OrderPlacedDto();
            orderPlacedDto.setOrderId(order.getOrderId());
            orderPlacedDto.setOrderStatus(order.getOrderStatus());
            orderPlacedDto.setOrderShippingAddressline1(order.getOrderShippingAddressline1());
            orderPlacedDto.setOrderShippingAddressline2(order.getOrderShippingAddressline2());
            orderPlacedDto.setOrderShippingCharges(order.getOrderShippingCharges());
            orderPlacedDto.setOrderShippingCity(order.getOrderShippingCity());
            orderPlacedDto.setOrderShippingState(order.getOrderShippingState());
            orderPlacedDto.setOrderShippingZip(order.getOrderShippingZip());

        return orderPlacedDto;
    }
}
