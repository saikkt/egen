package com.egen.order.model.domain;

import com.egen.order.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_customer_id")
    @NotNull(message = "Customer Id is required")
    private String orderCustomerId;

    @Column(name = "order_date")
    private Date orderDate;

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
    private BigDecimal orderShippingCharges;

    @Transient
    private String orderShippingAddressline1;

    @Transient
    private String orderShippingAddressline2;

    @Transient
    private String orderShippingCity;

    @Transient
    private String orderShippingState;

    @Transient
    private String orderShippingZip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<Payment> payments = new ArrayList<>();

    @Version
    private long version;

    protected Order(){
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = new Date(new java.util.Date().getTime());
    }

}
