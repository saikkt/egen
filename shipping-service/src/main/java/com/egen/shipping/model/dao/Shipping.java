package com.egen.shipping.model.dao;

import com.egen.shipping.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "shipping")
@Getter
@Setter
public class Shipping implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "shipping_id")
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_shipping_charges")
    private BigDecimal orderShippingCharges;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_shipping_addressline1")
    private String orderShippingAddressline1;

    @Column(name = "order_shipping_addressline2")
    private String orderShippingAddressline2;

    @Column(name = "order_shipping_city")
    private String orderShippingCity;

    @Column(name = "order_shipping_state")
    private String orderShippingState;

    @Column(name = "order_shipping_zip")
    private String orderShippingZip;

    public Shipping() {
    }
}