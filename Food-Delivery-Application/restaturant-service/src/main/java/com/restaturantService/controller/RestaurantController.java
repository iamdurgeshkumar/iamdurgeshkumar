package com.restaturantService.controller;

import com.restaturantService.Service.RestaurantService;
import com.restaturantService.dto.RestaurantRequest;
import com.restaturantService.dto.RestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public RestaurantResponse create(@RequestBody RestaurantRequest request) {
        log.info("Inside control layer create api ");
        return restaurantService.create(request);
    }

    @PutMapping("/update/{id}")
    public RestaurantResponse update(@PathVariable Integer id, @RequestBody RestaurantRequest request) {
        log.info("Inside control layer :: update api ");
        return restaurantService.update(id, request);
    }

    @GetMapping("getAll")
    public List<RestaurantResponse> getAll() {
        log.info("Inside control layer ::get all api ");
        return restaurantService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("inside control layer :: get delete api");
        restaurantService.delete(id);
        return "Restaurant deleted successfully !!";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        log.info("Inside control  layer :: get all delete");
        restaurantService.deleteAll();
        return "Restaurant data all deleted successFully !!";
    }
}
