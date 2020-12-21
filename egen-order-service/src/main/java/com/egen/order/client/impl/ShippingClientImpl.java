package com.egen.order.client.impl;

import com.egen.order.client.ShippingClient;
import com.egen.order.config.ShippingClientConfig;
import com.egen.order.exception.OrderServiceException;
import com.egen.order.model.client.OrderPlacedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Component
@Slf4j
public class ShippingClientImpl implements ShippingClient {

    private final RestTemplate restTemplate;
    private final ShippingClientConfig shippingClientConfig;

    public ShippingClientImpl(RestTemplateBuilder restTemplateBuilder, ShippingClientConfig shippingClientConfig) {
        this.restTemplate = restTemplateBuilder
                .build();
        this.shippingClientConfig = shippingClientConfig;
    }

    /*
    ToDO: Endpoint is not working when circuit breaker is implemented. Should check the issue.
     */
    @Override
    public Void orderPlaced(OrderPlacedDto orderPlacedDto) {

        HttpEntity<OrderPlacedDto> httpEntity = new HttpEntity<>(orderPlacedDto);

        ResponseEntity<Void> clientResponse = restTemplate.exchange(shippingClientConfig.getUrl(), HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Void>() {
        });

        handleClientResponse(clientResponse);

        return null;
    }

    public Void orderPlacedFallback(OrderPlacedDto orderPlacedDto){
        log.info("Fall back occurred while calling shipping service");
        throw new OrderServiceException("Fallback occurred");
    }

    private void handleClientResponse(ResponseEntity<Void> clientResponse){

        if(clientResponse == null){
            log.error("Shipping-Service response entity is null, down stream service error");
            throw new OrderServiceException("Down stream service error");
        }

        if(!clientResponse.getStatusCode().is2xxSuccessful()){
            log.error("Shipping-Service Call Failure, HttpStatusCode: {}", clientResponse.getStatusCodeValue());
            throw new OrderServiceException("Down stream service error");
        }

        log.debug("Shipping-Service Success, returning response : {}",clientResponse);

    }
}
