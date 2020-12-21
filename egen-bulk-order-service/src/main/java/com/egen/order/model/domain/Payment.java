package com.egen.order.model.domain;

import com.egen.order.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @Column(name = "payment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_confirmation_number")
    private String paymentConfirmationNumber;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


}
