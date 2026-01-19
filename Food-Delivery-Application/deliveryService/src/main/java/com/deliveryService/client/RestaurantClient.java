package com.deliveryService.client;

import com.deliveryService.dto.OrderResponse;
import com.deliveryService.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantClient {

    @Autowired
    public RestTemplate restTemplate;

   public void validateOrder(Long orderId) {
        String url = "http://localhost:8080/order/get/" + orderId;
        try {
            restTemplate.getForObject(url, OrderResponse.class, orderId);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
