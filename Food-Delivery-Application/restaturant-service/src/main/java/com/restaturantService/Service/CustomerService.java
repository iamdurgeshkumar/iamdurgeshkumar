package com.restaturantService.Service;

import com.restaturantService.dto.CustomerRequest;
import com.restaturantService.dto.CustomerResponse;
import com.restaturantService.dto.OrderRequest;
import com.restaturantService.dto.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Integer id, CustomerRequest request);

    CustomerResponse get(Integer id);

     List<CustomerResponse> getAll();

     void  delete(Integer id);

}
