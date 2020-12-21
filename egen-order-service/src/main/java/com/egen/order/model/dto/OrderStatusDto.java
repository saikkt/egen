package com.egen.order.model.dto;

import com.egen.order.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OrderStatusDto implements Serializable {

    private String orderId;
    private OrderStatus orderStatus;

}
