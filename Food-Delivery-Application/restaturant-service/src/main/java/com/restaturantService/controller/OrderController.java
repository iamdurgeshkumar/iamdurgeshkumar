package com.restaturantService.controller;

import com.restaturantService.Service.OrderService;
import com.restaturantService.dto.OrderRequest;
import com.restaturantService.dto.OrderResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderResponse create(@RequestBody OrderRequest request,
                                @RequestParam Integer customerId,
                                @RequestParam List<Integer> menuItemsId) {
        log.info("Inside control layer ::create api");
        return orderService.create(request,customerId,menuItemsId);
    }

    @PutMapping("/update/{id}")
    public OrderResponse update(@PathVariable Integer id, @RequestBody OrderRequest request) {
        log.info("inside control layer::update api ");
        return orderService.update(id, request);
    }

    @GetMapping("/get/{id}")
    public OrderResponse get(@PathVariable Integer id) {
        log.info("inside control layer :get api");
        return orderService.get(id);
    }

    @GetMapping("/getAll")
    public List<OrderResponse> getAll() {
        log.info("inside control layer :: get all");
        return orderService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("inside control layer :: delete api");
        orderService.delete(id);
        return "Order deleted successfully !!";
    }


}
