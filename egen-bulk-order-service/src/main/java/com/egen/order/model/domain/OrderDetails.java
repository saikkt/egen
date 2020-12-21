package com.egen.order.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_item_id")
    private String orderItemId;

    @Column(name = "order_id")
    private String orderId;

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
