package com.restaturantService.Service.Impl;

import com.restaturantService.Service.MenuItemService;
import com.restaturantService.dto.MenuItemRequest;
import com.restaturantService.dto.MenuItemResponse;
import com.restaturantService.entity.MenuItem;
import com.restaturantService.entity.Restaurant;
import com.restaturantService.exception.BadRequestNotFoundException;
import com.restaturantService.exception.NotFoundException;
import com.restaturantService.repository.MenuItemRepository;
import com.restaturantService.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public MenuItemResponse create(MenuItemRequest request, Integer restaurantId) {
        log.info("Inside service layer implement the create api");
        MenuItem menuItem = modelMapper.map(request, MenuItem.class);
        Restaurant restaurant = restaurantRepository
                .findByIdAndIsDeletedFalse(restaurantId).orElseThrow(() -> new BadRequestNotFoundException("Restaurant id not found:" + restaurantId));
        menuItem.setRestaurant(restaurant);
        MenuItem save = menuItemRepository.save(menuItem);
        return modelMapper.map(save, MenuItemResponse.class);
    }

    @Override
    public MenuItemResponse update(Integer id, MenuItemRequest request) {
        log.info("Inside service layer implement the update api ");
        MenuItem menuItem = menuItemRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NotFoundException("menuItem not found" + id));
        modelMapper.map(request, menuItem);
        MenuItem menuItem1 = menuItemRepository.save(menuItem);
        return modelMapper.map(menuItem1, MenuItemResponse.class);
    }

    @Override
    public MenuItemResponse get(Integer id) {
        log.info("Inside service layer implement the get method ");
        MenuItem menuItem = menuItemRepository
                .findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NotFoundException("MenuItem id not found:" + id));
        return modelMapper.map(menuItem, MenuItemResponse.class);
    }

    @Override
    public List<MenuItemResponse> getAll() {
        log.info("Inside service layer implement the get all method  ");
        List<MenuItem> menuItemList = menuItemRepository.findAllByIsDeletedFalse();
        return modelMapper.map(menuItemList, new TypeToken<List<MenuItemResponse>>() {
        }.getType());
    }

    @Override
    public void delete(Integer id) {
        log.info("Inside service layer implement the delete method ");
        MenuItem menuItem = menuItemRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("MenuItem not found with id: " + id));
        menuItem.setDeleted(true);
        menuItemRepository.save(menuItem);
    }


    @Override
    public void deleteAll() {
        log.info("Inside service layer implement the delete all method");
        List<MenuItem> menuItemList = menuItemRepository.findAllByIsDeletedFalse();
        menuItemList.forEach(menuItem -> {
            menuItem.setDeleted(true);
            menuItemRepository.saveAll(menuItemList);
        });
    }

    @Override
    public Page<MenuItemResponse> findAllMenu(Integer page, Integer size,
                                              String sortBy, String sorting) {
        log.info("Inside service layer implement the page api");
        Sort.Direction direction = sorting.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<MenuItem> pageList = menuItemRepository.findAll(pageRequest);
        List<MenuItemResponse> menuItemResponseList = pageList.stream().map(pageItem -> modelMapper.map(pageItem, MenuItemResponse.class))
                .toList();
        return new PageImpl<>(menuItemResponseList,pageRequest,pageList.getTotalElements());
    }
}
