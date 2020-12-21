package com.egen.order.service.impl;

import com.egen.order.mapper.OrderEventMapper;
import com.egen.order.model.client.OrderEvent;
import com.egen.order.model.domain.Order;
import com.egen.order.repository.BulkOrderRepository;
import com.egen.order.service.BulkOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BulkOrderServiceImpl implements BulkOrderService {

    private final BulkOrderRepository bulkOrderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createBulkOrders(List<Order> orders) {
      List<Order> orderList =  bulkOrderRepository.saveAll(orders);
      publishBulkOrders(orderList);
    }

    private void publishBulkOrders(List<Order> orders){
        List<OrderEvent> orderEvents = OrderEventMapper.toOrderEvent(orders);

        orderEvents.stream().forEach(orderEvent -> {
            try {
                rabbitTemplate.convertAndSend("order-events","",
                        objectMapper.writeValueAsString(orderEvent));
            } catch (JsonProcessingException e) {
                log.debug("Unable to process the order event", e);
            }
        });
    }

    @Override
    public void deleteOrderById(long orderId) {
        bulkOrderRepository.deleteById(orderId);
    }

    @Override
    public void deleteAllInBatch(List<Order> orders) {
        bulkOrderRepository.deleteInBatch(orders);
    }
}
