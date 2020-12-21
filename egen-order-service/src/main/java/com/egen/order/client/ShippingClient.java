package com.egen.order.client;

import com.egen.order.model.client.OrderPlacedDto;

public interface ShippingClient {

    Void orderPlaced(OrderPlacedDto orderPlacedDto);
}
