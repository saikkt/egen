package com.egen.order.model.client;

import com.egen.order.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderEvent implements Serializable {

    private Long order_id;
    private OrderStatus orderStatus;
    private BigDecimal orderShippingCharges;
    private String ordershippingaddressline1;
    private String orderShippingAddressline2;
    private String orderShippingCity;
    private String orderShippingState;
    private String orderShippingZip;

}
