package com.egen.shipping.controller;

import com.egen.shipping.model.domain.OrderPlaced;
import com.egen.shipping.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shipping/v1")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/order-placed")
    public ResponseEntity<Void> postShipping(@RequestBody OrderPlaced orderPlaced){
        shippingService.save(orderPlaced);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }
}
