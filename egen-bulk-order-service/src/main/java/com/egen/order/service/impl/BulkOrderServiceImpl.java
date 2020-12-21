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
import org.hibernate.SessionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Order> tempOrderList = new ArrayList<>();

        for(int i = 0; i < orders.size() ; i++){

            tempOrderList.add(orders.get(i));

            if(i % 5 == 0 && i > 0){
                publishBulkOrders(bulkOrderRepository.saveAll(tempOrderList));
                tempOrderList.clear();
            }
        }
        if(tempOrderList.size() > 0){
            publishBulkOrders(bulkOrderRepository.saveAll(tempOrderList));
        }
    }

    private void publishBulkOrders(List<Order> orders){
        List<OrderEvent> orderEvents = OrderEventMapper.toOrderEvent(orders);
        try {
            rabbitTemplate.convertAndSend("order-events","",
                    objectMapper.writeValueAsString(orderEvents));
        } catch (JsonProcessingException e) {
            log.debug("Unable to process the order event", e);
        }
    }

    @Override
    public void deleteOrderById(String orderId) {
        bulkOrderRepository.deleteById(orderId);
    }

    @Override
    public void deleteAllInBatch(List<Order> orders) {
        bulkOrderRepository.deleteInBatch(orders);
    }
}
