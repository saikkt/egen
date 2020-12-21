package com.egen.shipping.model.domain;

import com.egen.shipping.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderPlaced implements Serializable {

    private String orderId;
    private OrderStatus orderStatus;
    private BigDecimal orderShippingCharges;
    private String orderShippingAddressline1;
    private String orderShippingAddressline2;
    private String orderShippingCity;
    private String orderShippingState;
    private String orderShippingZip;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderShippingCharges() {
        return orderShippingCharges;
    }

    public void setOrderShippingCharges(BigDecimal orderShippingCharges) {
        this.orderShippingCharges = orderShippingCharges;
    }

    public String getOrderShippingAddressline1() {
        return orderShippingAddressline1;
    }

    public void setOrderShippingAddressline1(String orderShippingAddressline1) {
        this.orderShippingAddressline1 = orderShippingAddressline1;
    }

    public String getOrderShippingAddressline2() {
        return orderShippingAddressline2;
    }

    public void setOrderShippingAddressline2(String orderShippingAddressline2) {
        this.orderShippingAddressline2 = orderShippingAddressline2;
    }

    public String getOrderShippingCity() {
        return orderShippingCity;
    }

    public void setOrderShippingCity(String orderShippingCity) {
        this.orderShippingCity = orderShippingCity;
    }

    public String getOrderShippingState() {
        return orderShippingState;
    }

    public void setOrderShippingState(String orderShippingState) {
        this.orderShippingState = orderShippingState;
    }

    public String getOrderShippingZip() {
        return orderShippingZip;
    }

    public void setOrderShippingZip(String orderShippingZip) {
        this.orderShippingZip = orderShippingZip;
    }
}
