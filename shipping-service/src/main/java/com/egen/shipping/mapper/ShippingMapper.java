package com.egen.shipping.mapper;

import com.egen.shipping.model.dao.Shipping;
import com.egen.shipping.model.domain.OrderPlaced;

import java.util.ArrayList;
import java.util.List;

public class ShippingMapper {

    public static Shipping toShipping(OrderPlaced orderPlaced){
        Shipping shipping = new Shipping();

        shipping.setOrderId(orderPlaced.getOrderId());
        shipping.setOrderStatus(orderPlaced.getOrderStatus());
        shipping.setOrderShippingAddressline1(orderPlaced.getOrderShippingAddressline1());
        shipping.setOrderShippingAddressline2(orderPlaced.getOrderShippingAddressline2());
        shipping.setOrderShippingCharges(orderPlaced.getOrderShippingCharges());
        shipping.setOrderShippingCity(orderPlaced.getOrderShippingCity());
        shipping.setOrderShippingState(orderPlaced.getOrderShippingState());
        shipping.setOrderShippingZip(orderPlaced.getOrderShippingZip());

        return shipping;
    }

    public static List<Shipping> toShipping(List<OrderPlaced> placedOrders){

        List<Shipping> shippings = new ArrayList<>();
        placedOrders.forEach(orderPlaced -> {
            Shipping shipping = new Shipping();
            shipping.setOrderId(orderPlaced.getOrderId());
            shipping.setOrderStatus(orderPlaced.getOrderStatus());
            shipping.setOrderShippingAddressline1(orderPlaced.getOrderShippingAddressline1());
            shipping.setOrderShippingAddressline2(orderPlaced.getOrderShippingAddressline2());
            shipping.setOrderShippingCharges(orderPlaced.getOrderShippingCharges());
            shipping.setOrderShippingCity(orderPlaced.getOrderShippingCity());
            shipping.setOrderShippingState(orderPlaced.getOrderShippingState());
            shipping.setOrderShippingZip(orderPlaced.getOrderShippingZip());

            shippings.add(shipping);
        });
        return shippings;
    }
}
