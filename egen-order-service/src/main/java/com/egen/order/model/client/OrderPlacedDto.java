package com.egen.order.model.client;

import com.egen.order.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderPlacedDto implements Serializable {

    private String orderId;
    private OrderStatus orderStatus;
    private BigDecimal orderShippingCharges;
    private String orderShippingAddressline1;
    private String orderShippingAddressline2;
    private String orderShippingCity;
    private String orderShippingState;
    private String orderShippingZip;

}
