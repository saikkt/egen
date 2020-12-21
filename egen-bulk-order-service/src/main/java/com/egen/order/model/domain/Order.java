package com.egen.order.model.domain;

import com.egen.order.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_customer_id")
    @NotNull(message = "Customer Id is required")
    private String orderCustomerId;

    @Column(name = "order_total")
    private BigDecimal orderTotal;

    @Column(name = "order_subtotal")
    private BigDecimal orderSubtotal;

    @Column(name = "order_tax")
    private BigDecimal orderTax;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Transient
    @JsonInclude
    private BigDecimal orderShippingCharges;

    @Transient
    @JsonInclude
    @NotNull(message = "Shipping Address is required")
    private String orderShippingAddressline1;

    @Transient
    @JsonInclude
    private String orderShippingAddressline2;

    @Transient
    @JsonInclude
    @NotEmpty(message = "Shipping city is required")
    private String orderShippingCity;

    @Transient
    @JsonInclude
    @NotEmpty(message = "Shipping state is required")
    private String orderShippingState;

    @Transient
    @JsonInclude
    @NotEmpty(message = "Shipping ZipCode is required")
    private String orderShippingZip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    @NotEmpty(message = "Order should contain at least one item")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @NotEmpty(message = "At least one payment should exist")
    private List<Payment> payments = new ArrayList<>();

    @Version
    private long version;

    protected Order(){
        this.orderStatus = OrderStatus.PENDING;
    }

}
