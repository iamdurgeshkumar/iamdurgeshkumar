package com.restaturantService.Service;

import com.restaturantService.dto.RestaurantRequest;
import com.restaturantService.dto.RestaurantResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface RestaurantService {

     RestaurantResponse create(RestaurantRequest request);

     RestaurantResponse update(Integer id, RestaurantRequest request);

     List<RestaurantResponse> getAll();

     void delete(Integer id);

     void deleteAll();

}
