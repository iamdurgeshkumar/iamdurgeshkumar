package com.restaturantService.Service;

import com.restaturantService.dto.OrderRequest;
import com.restaturantService.dto.OrderResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface OrderService {

    OrderResponse create(OrderRequest request, Integer customerId, List<Integer> menuItemsId);

    OrderResponse update(Integer id, OrderRequest request);

    OrderResponse get(Integer id);

    List<OrderResponse> getAll();

    void delete(Integer id);
}
