package com.egen.shipping.worker;

import com.egen.shipping.mapper.ShippingMapper;
import com.egen.shipping.model.dao.Shipping;
import com.egen.shipping.model.domain.OrderPlaced;
import com.egen.shipping.repository.ShippingRepository;
import com.egen.shipping.service.ShippingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShippingEventsWorker {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ShippingService shippingService;
    private final ShippingRepository shippingRepository;

    @Column(name = "shipping_id")
    private long id;
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "order_shipping_addressline1")
    private String addressLine1;
    @Column(name = "order_shipping_addressline2")
    private String addressLine2;
    @Column(name = "order_shipping_city")
    private String city;
    @Column(name = "order_shipping_state")
    private String state;
    @Column(name = "order_shipping_zip")
    private int zip;
    @Column(name = "order_shipping_country")
    private String country;

    @RabbitListener(queues = "order-events-queue")
    public void receiveEvent(String message) {
        try {
            List<OrderPlaced> placedOrders = objectMapper.readValue(message,
                    new TypeReference<List<OrderPlaced>>(){});

            List<Shipping> shippings = placedOrders.stream()
                    .map(ShippingMapper::toShipping)
                    .collect(Collectors.toList());

           shippingRepository.saveAll(shippings);
        } catch (JsonProcessingException e) {
           log.error("Unable to parse the message");
        }
    }
}
