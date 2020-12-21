package com.egen.order.controller;

import com.egen.order.model.domain.Order;
import com.egen.order.service.BulkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/v1")
@RequiredArgsConstructor
public class BulkOrderController {

    private final BulkOrderService bulkOrderService;

    @PostMapping
    public ResponseEntity<Void> createBulkOrders(@RequestBody List<Order> orders) {
        bulkOrderService.createBulkOrders(orders);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(long orderId){
        bulkOrderService.deleteOrderById(orderId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInBulk(List<Order> orders){
        bulkOrderService.deleteAllInBatch(orders);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

}
