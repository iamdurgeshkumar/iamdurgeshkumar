package com.deliveryService.controller;

import com.deliveryService.dto.DeliveryRequest;
import com.deliveryService.dto.DeliveryResponse;
import com.deliveryService.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;


    @PostMapping("/create")
    public DeliveryResponse create(@RequestBody DeliveryRequest request) {

        return deliveryService.create(request);
    }

    @PutMapping("/update/{id}")
    public DeliveryResponse update(@PathVariable String id, @RequestBody DeliveryRequest request) {
        return deliveryService.update(id, request);
    }

    @GetMapping("/get/{id}")
    public DeliveryResponse get(@PathVariable String id) {
        return deliveryService.get(id);
    }

    @GetMapping("/getAll")
    public List<DeliveryResponse> getAll() {
        return deliveryService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        deliveryService.delete(id);
        return "delete delivery service successfully!!";
    }

    @GetMapping("/pageRequest")
    public Page<DeliveryResponse> findAll(@RequestParam Integer page,
                                          @RequestParam Integer size,
                                          @RequestParam String sortBy,
                                          @RequestParam String sorting) {
        return deliveryService.findAll(page, size, sortBy, sorting);
    }
}
