package com.deliveryService.service.Impl;

import com.deliveryService.client.RestaurantClient;
import com.deliveryService.client.RestaurantFeignClient;
import com.deliveryService.dto.DeliveryRequest;
import com.deliveryService.dto.DeliveryResponse;
import com.deliveryService.dto.OrderResponse;
import com.deliveryService.entity.Delivery;
import com.deliveryService.exception.BadRequestFoundException;
import com.deliveryService.exception.NotFoundException;
import com.deliveryService.repository.DeliveryRepository;
import com.deliveryService.service.DeliveryService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private RestaurantClient restaurantClient;

    @Autowired
    private RestaurantFeignClient restaurantFeignClient;


    @Override
    public DeliveryResponse create(DeliveryRequest request) {
        if (request == null) {
            throw new BadRequestFoundException("Delivery request cannot be null");
        }
        validateOrder(request);

        Delivery delivery = modelMapper.map(request, Delivery.class);
        delivery = deliveryRepository.save(delivery);
        return modelMapper.map(delivery, DeliveryResponse.class);
    }

    private void validateOrder(DeliveryRequest request) {
        try {
            OrderResponse orderResponse = restaurantFeignClient.get(request.getOrderId());
            if (orderResponse == null) {
                throw new NotFoundException("Order not found for id " + request.getOrderId());
            }
        } catch (Exception e) {
            throw new NotFoundException("Order not found with id "+ request.getOrderId());
        }
    }

    @Override
    public DeliveryResponse update(String id, DeliveryRequest request) {
        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(id).
                orElseThrow(() -> new NotFoundException("Delivery not found with id" + id));
        modelMapper.map(request, delivery);
        Delivery delivery1 = deliveryRepository.save(delivery);
        return modelMapper.map(delivery1, DeliveryResponse.class);
    }

    @Override
    public DeliveryResponse get(String id) {
        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new BadRequestFoundException("found bad request with id " + id));
        return modelMapper.map(delivery, DeliveryResponse.class);
    }

    @Override
    public List<DeliveryResponse> getAll() {
        List<Delivery> deliveryList = deliveryRepository.findAllByIsDeletedFalse();
        return modelMapper.map(deliveryList, new TypeToken<List<DeliveryResponse>>() {
        }.getType());
    }

    @Override
    public void delete(String id) {
        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("id not found for id ::" + id));
        delivery.setDeleted(true);
        deliveryRepository.save(delivery);
        try{
            String string = restaurantFeignClient.delete(Integer.valueOf(id));
            if(string==null) {
                throw  new BadRequestFoundException("id not found ");
            }
        }catch (Exception e){
            throw  new NotFoundException("id not found with id"+id);
        }
    }

    @Override
    public Page<DeliveryResponse> findAll(Integer page, Integer size, String sortBy, String sorting) {
        Sort.Direction direction = sorting.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        List<Delivery> deliveryList = deliveryRepository.findAll();
        List<DeliveryResponse> deliveryResponseList = deliveryList.stream().map(entry -> modelMapper.map(entry, DeliveryResponse.class)).toList();
        return new PageImpl<>(deliveryResponseList);
    }
}
