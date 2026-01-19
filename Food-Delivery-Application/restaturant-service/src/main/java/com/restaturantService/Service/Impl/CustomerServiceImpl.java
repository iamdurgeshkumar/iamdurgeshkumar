package com.restaturantService.Service.Impl;

import com.restaturantService.Service.CustomerService;
import com.restaturantService.dto.CustomerRequest;
import com.restaturantService.dto.CustomerResponse;
import com.restaturantService.entity.Customer;
import com.restaturantService.entity.Order;
import com.restaturantService.exception.BadRequestNotFoundException;
import com.restaturantService.exception.NotFoundException;
import com.restaturantService.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        log.info("inside service layer :: implement the create api");
        Customer customer = modelMapper.map(request, Customer.class);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(Integer id, CustomerRequest request) {
        log.info("Inside service layer :: implement the update api ");
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Customer id  not found " + id));
        modelMapper.map(request, customer);
        Customer save = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse get(Integer id) {
        log.info("inside service layer ::implement the get api");
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestNotFoundException("Bad Request: not found with id " + id));
        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAll() {
        log.info("inside service layer :: get api ");
        List<Customer> customerResponseList = customerRepository.findAllByIsDeletedFalse();
        return modelMapper.map(customerResponseList, new TypeToken<List<CustomerResponse>>() {
        }.getType());

    }

    @Override
    public void delete(Integer id) {
        log.info("Inside service layer ::delete api");
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("customer id not found :" + id));
        customer.setDeleted(true);
        customerRepository.save(customer);
    }
}
