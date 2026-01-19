package com.deliveryService.service;

import com.deliveryService.dto.DeliveryRequest;
import com.deliveryService.dto.DeliveryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface DeliveryService {

    DeliveryResponse create(DeliveryRequest request);

    DeliveryResponse update(String id,DeliveryRequest request);

    DeliveryResponse get(String id );

     List<DeliveryResponse> getAll();

     void  delete(String id);

    Page<DeliveryResponse> findAll(@RequestParam Integer page,
                                          @RequestParam Integer size,
                                          @RequestParam String sortBy,
                                          @RequestParam String sorting);
}
