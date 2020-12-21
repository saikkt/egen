package com.egen.order.service.impl;

import com.egen.order.client.ShippingClient;
import com.egen.order.exception.OrderServiceException;
import com.egen.order.mapper.OrderMapper;
import com.egen.order.model.client.OrderPlacedDto;
import com.egen.order.model.domain.Order;
import com.egen.order.model.domain.OrderDetails;
import com.egen.order.model.domain.Payment;
import com.egen.order.model.dto.OrderStatusDto;
import com.egen.order.model.enums.OrderStatus;
import com.egen.order.repository.OrderRepository;
import com.egen.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShippingClient shippingClient;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    @Override
    public OrderStatusDto createOrder(Order order){
        validateShippingData(order);
        validateOrderAmount(order);
        setOrderStatus(order);
        Order savedOrder = orderRepository.saveAndFlush(order);
       // System.out.println(savedOrder.toString());
        log.trace("Order Saved Successfully");

        //Send order for shipping only when order status is placed or payment is completed.
        if(order.getOrderStatus().equals(OrderStatus.PLACED)) {
            placeOrder(OrderMapper.toOrderPlacedDto(order));
        }
        return new OrderStatusDto(savedOrder.getOrderId(), savedOrder.getOrderStatus());
    }

    private void validateShippingData(Order order){

        if(order.getOrderShippingAddressline1() == null ||
            order.getOrderShippingCharges() == null ||
            order.getOrderShippingCity() == null ||
        order.getOrderShippingState() == null ||
                order.getOrderShippingZip() == null){
            throw new OrderServiceException("Shipping details cannot be null");
        }
    }

    private void validatePaymentData(Payment payment){
        if(payment.getPaymentConfirmationNumber() == null){
            throw new OrderServiceException("Payment Confirmation Number cannot be null");
        }
        if(payment.getPaymentAmount() == null){
            throw new OrderServiceException("Payment Amount cannot be null");
        }
    }


    private void setOrderStatus(Order order) {

            BigDecimal orderTotal = order.getOrderTotal();
            BigDecimal paymentTotal = BigDecimal.ZERO;
            for (Payment payment : order.getPayments()) {
               paymentTotal = paymentTotal.add(payment.getPaymentAmount());
            }
            if (orderTotal.compareTo(paymentTotal) == 0) {
                order.setOrderStatus(OrderStatus.PLACED);
            }
    }

    private void validateOrderAmount(Order order){
        BigDecimal totalAmount = order.getOrderTotal();
        BigDecimal totalOrderItemsAmount = BigDecimal.ZERO;
        for(OrderDetails orderDetail : order.getOrderDetails()){
            totalOrderItemsAmount = totalOrderItemsAmount.add(
                    orderDetail.getOrderItemUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getOrderItemQuantity())));
        }

        if(!(totalAmount.equals(totalOrderItemsAmount.add(order.getOrderShippingCharges()).add(order.getOrderTax())))){
            log.error("Total Order Amount Mismatched");
            throw new OrderServiceException("Total Amount Mismatched");
        }

    }

    private void placeOrder(OrderPlacedDto orderPlacedDto){
        shippingClient.orderPlaced(orderPlacedDto);
    }

    @Override
    public Order getOrderById(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(!order.isPresent()){
            throw new OrderServiceException("Unable to find order details for given order id.");
        }

        return order.get();
    }

    @Override
    public Void deleteOrderById(String orderId) {
        orderRepository.deleteById(orderId);
        return null;
    }

    @Override
    @Transactional
    public Order updateOrderPayment(String orderId, Payment payment) {

        Optional<Order> order = orderRepository.findById(orderId);

        if(! order.isPresent()){
            throw new OrderServiceException("Unable to find the order.");
        }

        Order retrievedOrder = order.get();
        Payment payment1 = new Payment();
        payment1.setOrderId(retrievedOrder.getOrderId());
        payment1.setPaymentAmount(payment.getPaymentAmount());
        payment1.setPaymentConfirmationNumber(payment.getPaymentConfirmationNumber());
        payment1.setPaymentType(payment.getPaymentType());

        retrievedOrder.getPayments().add(payment1);

        Order updatedOrder = orderRepository.save(retrievedOrder);

        setOrderStatus(updatedOrder);

        if(updatedOrder.getOrderStatus().equals(OrderStatus.PLACED)){
            placeOrder(OrderMapper.toOrderPlacedDto(updatedOrder));
        }

        return updatedOrder;
    }

    @Override
    public List<Order> getOrderHistory(String customerId) {
        List<Order> orders = orderRepository.findByOrderCustomerId(customerId);
        if(orders.isEmpty()){
            throw new OrderServiceException("No orders found");
        }
        return orders;
    }


}
