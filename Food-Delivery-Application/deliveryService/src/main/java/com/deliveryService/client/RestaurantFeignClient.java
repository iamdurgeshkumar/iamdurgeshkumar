package com.deliveryService.client;

import com.deliveryService.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "RestaurantService", url = "http://localhost:8080")
public interface RestaurantFeignClient {

    @GetMapping("/order/get/{id}")
    public OrderResponse get(@PathVariable Integer id);

    @DeleteMapping("order/delete/{id}")
    public String delete(@PathVariable Integer id);
}
