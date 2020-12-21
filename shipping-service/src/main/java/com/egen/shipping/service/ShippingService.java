package com.egen.shipping.service;

import com.egen.shipping.mapper.ShippingMapper;
import com.egen.shipping.model.dao.Shipping;
import com.egen.shipping.model.domain.OrderPlaced;
import com.egen.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public void save(OrderPlaced orderPlaced) {

        Shipping shipping = ShippingMapper.toShipping(orderPlaced);
        shippingRepository.save(shipping);
    }
}
