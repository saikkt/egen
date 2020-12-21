package com.egen.order.model.domain;

import com.egen.order.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @Column(name = "payment_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String paymentId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "payment_confirmation_number")
    private String paymentConfirmationNumber;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    protected Payment(){
    }


}
