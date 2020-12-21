package com.egen.order.controller;

import com.egen.order.model.domain.Order;
import com.egen.order.model.domain.Payment;
import com.egen.order.model.dto.OrderStatusDto;
import com.egen.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderStatusDto> createOrder(@Valid
            @RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/update-payment/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Payment payment){
        return new ResponseEntity<>(orderService.updateOrderPayment(orderId, payment), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "orderId") String orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable(name = "orderId") String orderId){
        return new ResponseEntity<Void>(orderService.deleteOrderById(orderId),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order-history/{customerId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getOrderHistory(customerId));
    }

}
