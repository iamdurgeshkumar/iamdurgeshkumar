package com.restaturantService.Service.Impl;

import com.restaturantService.Service.OrderService;
import com.restaturantService.entity.DeliveryRequest;
import com.restaturantService.orderProducer.OrderProducer;
import com.restaturantService.dto.OrderRequest;
import com.restaturantService.dto.OrderResponse;
import com.restaturantService.entity.Customer;
import com.restaturantService.entity.MenuItem;
import com.restaturantService.entity.Order;
import com.restaturantService.exception.BadRequestNotFoundException;
import com.restaturantService.exception.NotFoundException;
import com.restaturantService.repository.CustomerRepository;
import com.restaturantService.repository.MenuItemRepository;
import com.restaturantService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.restaturantService.dto.DeliveryStatus.DELIVERING;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderProducer orderProducer;

    @Override
    public OrderResponse create(OrderRequest request, Integer customerId, List<Integer> menuItemsIds) {
        log.info("Inside service layer ::implement the create api");

        // Map request to entity
        Order order = modelMapper.map(request, Order.class);

        // Fetch customer
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(customerId)
                .orElseThrow(() -> new NotFoundException("customerId not found with id " + customerId));
        order.setCustomer(customer);

        // Fetch menu items
        List<MenuItem> menuItemList = menuItemRepository.findAllById(menuItemsIds);
        if (menuItemList.isEmpty()) {
            throw new BadRequestNotFoundException("menuItemIdNotFound with id " + menuItemsIds);
        }
        order.setMenuItems(menuItemList);

        // Set order date and save
        order.setOrderDate(LocalDateTime.now());
        order = orderRepository.save(order);

        // Create DeliveryRequest for Kafka
        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setOrderId(order.getId());
        deliveryRequest.setDeliveryStatus(DELIVERING);
        deliveryRequest.setDeliveryTime(LocalDate.now());

        try {
            orderProducer.publishCreateOrder(deliveryRequest);
        } catch (Exception e) {
            log.error("Kafka publishing failed for orderId {}", order.getId(), e);
            // Optional: handle compensation if needed
        }

        // Return response
        return modelMapper.map(order, OrderResponse.class);
    }


    @Override
    public OrderResponse update(Integer id, OrderRequest request) {
        log.info("inside service layer ::implement the update api");
        Order order = orderRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("order id not found with id " + id));
        modelMapper.map(request, order);
        Order order1 = orderRepository.save(order);
        return modelMapper.map(order1, OrderResponse.class);
    }

    @Override
    public OrderResponse get(Integer id) {
        log.info("inside service layer :: implement the get api here ");
        Order order = orderRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("Order id  not found with id " + id));
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAll() {
        log.info("inside service layer :: implement the get all api");
        List<Order> orderList = orderRepository.findAllByIsDeletedFalse();
        return modelMapper.map(orderList, new TypeToken<List<OrderResponse>>() {
        }.getType());
    }

    @Override
    public void delete(Integer id) {
        log.info("inside service layer :: implement the delete api");
        Order order = orderRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("order id not found with  id " + id));
        order.setDeleted(true);
        orderRepository.save(order);
    }


}
