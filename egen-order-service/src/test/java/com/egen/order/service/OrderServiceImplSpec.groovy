package com.egen.order.service

import com.egen.order.client.ShippingClient
import com.egen.order.model.domain.Order
import com.egen.order.model.domain.OrderDetails
import com.egen.order.model.domain.Payment
import com.egen.order.model.enums.OrderStatus
import com.egen.order.repository.OrderRepository
import com.egen.order.service.impl.OrderServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class OrderServiceImplSpec extends Specification {

    OrderRepository orderRespository
    ShippingClient shippingClient
    ObjectMapper objectMapper = new ObjectMapper()
    OrderServiceImpl fixture

    def setup(){
        orderRespository = Mock()
        shippingClient = Mock()
        fixture = new OrderServiceImpl(orderRespository, shippingClient)
    }

    def "createOrder - success"(){
        given:
        def mockOrder = [
                orderCustomerId : "CUST1234",
                orderTotal: 35.00,
                orderSubtotal: 20.00,
                orderTax: 10.00,
                orderShippingCharges : 5.00,
                orderShippingAddressline1: "addressline1",
                orderShippingCity: "city",
                orderShippingState: "PA",
                orderShippingZip: "12345",
                orderDetails: [
                        [
                                orderItemName: "itemname",
                                orderItemQuantity: 2,
                                orderItemUnitPrice: 10.00,
                        ]as OrderDetails
                ],
                payments: [
                        [
                                "paymentConfirmationNumber": "PCN1234567",
                                "paymentAmount": "30",
                                "paymentType": "VISA"
                        ] as Payment,
                        [
                                "paymentConfirmationNumber": "PCN123456545",
                                "paymentAmount": "5",
                                "paymentType": "VISA"
                        ] as Payment
                ]
        ] as Order

        def mockSavedOrder = [
                orderId: "orderId",
                orderStatus: OrderStatus.PLACED
        ] as Order

        when:
        def result = fixture.createOrder(mockOrder)

        then:
        1 * orderRespository.save(*_) >> mockSavedOrder
        1 * shippingClient.orderPlaced(*_) >> new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE)
        result != null
    }

}
