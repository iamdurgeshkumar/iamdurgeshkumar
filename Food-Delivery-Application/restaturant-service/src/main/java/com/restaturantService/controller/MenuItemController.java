package com.restaturantService.controller;

import com.restaturantService.Service.MenuItemService;
import com.restaturantService.dto.MenuItemRequest;
import com.restaturantService.dto.MenuItemResponse;
import com.restaturantService.entity.Restaurant;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menuItem")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/create/{restaurantId}")
    public MenuItemResponse create(@RequestBody MenuItemRequest request, @PathVariable Integer restaurantId) {
        log.info("Inside control layer create api ");
        return menuItemService.create(request, restaurantId);
    }

    @PutMapping("/update/{id}")
    public MenuItemResponse update(@PathVariable Integer id, @RequestBody MenuItemRequest request) {
        log.info("Inside control layer update api ");
        return menuItemService.update(id, request);
    }

    @GetMapping("/get/{id}")
    public MenuItemResponse get(@PathVariable Integer id) {
        log.info("Inside control layer ::get method api  ");
        return menuItemService.get(id);
    }

    @GetMapping("/getAll")
    public List<MenuItemResponse> getAll() {
        log.info("inside control layer::get all api");
        return menuItemService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("Inside control  layer ::delete api ");
        menuItemService.delete(id);
        return "menuItem with id " + id + " deleted successfully  !!";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        log.info("delete all");
        menuItemService.deleteAll();
        return "menuItem deleted successfully !!";
    }

    @GetMapping("/PageRequest")
    public Page<MenuItemResponse> findAllMenu(@RequestParam Integer page
                                                ,@RequestParam Integer size
                                                , @RequestParam String sortBy
                                                ,@RequestParam String sorting){
        log.info("Inside control layer:: all data  page api  ");
        return menuItemService.findAllMenu(page, size, sortBy, sorting);
    }

}
