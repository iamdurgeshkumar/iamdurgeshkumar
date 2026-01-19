package com.restaturantService.controller;

import com.restaturantService.Service.CustomerService;
import com.restaturantService.dto.CustomerRequest;
import com.restaturantService.dto.CustomerResponse;
import com.restaturantService.dto.OrderRequest;
import com.restaturantService.dto.OrderResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public CustomerResponse create(@RequestBody CustomerRequest request) {
        log.info("inside control layer ::create api");
        return customerService.create(request);
    }

    @PutMapping("/update/{id}")
    public CustomerResponse update(@PathVariable Integer id, @RequestBody CustomerRequest request) {
        log.info("inside control layer::update api ");
        return customerService.update(id, request);
    }

    @GetMapping("/get/{id}")
    public CustomerResponse get(@PathVariable Integer id) {
        log.info("inside service layer ::get api");
        return customerService.get(id);
    }

    @GetMapping("/getAll")
    public List<CustomerResponse> getAll() {
        log.info("inside service layer :: get all api ");
        return customerService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("inside service layer ::delete api");
        customerService.delete(id);
        return "Customer deleted  successFully !!";
    }

}
