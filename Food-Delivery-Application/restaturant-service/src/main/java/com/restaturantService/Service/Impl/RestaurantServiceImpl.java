package com.restaturantService.Service.Impl;

import com.restaturantService.Service.RestaurantService;
import com.restaturantService.dto.RestaurantRequest;
import com.restaturantService.dto.RestaurantResponse;
import com.restaturantService.entity.MenuItem;
import com.restaturantService.entity.Restaurant;
import com.restaturantService.exception.BadRequestNotFoundException;
import com.restaturantService.exception.NotFoundException;
import com.restaturantService.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public RestaurantResponse create(RestaurantRequest request) {
        log.info("Inside service layer implement the create api");
        Restaurant restaurant = modelMapper.map(request, Restaurant.class);
        List<MenuItem> menuItems = restaurant.getMenuItems();
        if (menuItems != null && !menuItems.isEmpty()) {
            menuItems.forEach(menuItem -> {
                menuItem.setRestaurant(restaurant);
            });
            restaurant.setMenuItems(menuItems);
        }
        Restaurant newRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(newRestaurant, RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse update(Integer id, RestaurantRequest request) {
        log.info("Inside service layer implement the update api ");
        Restaurant restaurant = restaurantRepository
                .findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NotFoundException("Restaurant id not found : " + id));
        modelMapper.map(request, restaurant);
        restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant, RestaurantResponse.class);
    }

    @Override
    public List<RestaurantResponse> getAll() {
        log.info("Inside service layer implement the get all method ");
        List<Restaurant> restaurantList = restaurantRepository.findAllByIsDeletedFalse();
        return modelMapper.map(restaurantList, new TypeToken<List<RestaurantResponse>>() {
        }.getType());
    }

    @Override
    public void delete(Integer id) {
        log.info("Inside Service layer :: apply delete method");
        Restaurant restaurant = restaurantRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestNotFoundException("Restaurant id  not found " + id));
        restaurant.setDeleted(true);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteAll() {
        log.info("Inside service layer ::delete all ");
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        restaurantList.forEach(s -> s.setDeleted(true));
        restaurantRepository.saveAll(restaurantList);
    }
}
