package com.egen.order.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_item_name")
    @NotNull(message = "Item name is required")
    private String orderItemName;

    @Column(name = "order_item_quantity")
    @NotNull(message = "Item quantity is required")
    private int orderItemQuantity;

    @Column(name = "order_item_unit_price")
    @NotNull(message = "Item unit price is required")
    private BigDecimal orderItemUnitPrice;

    @Version
    private long version;

    public OrderDetails(){
    }
}
