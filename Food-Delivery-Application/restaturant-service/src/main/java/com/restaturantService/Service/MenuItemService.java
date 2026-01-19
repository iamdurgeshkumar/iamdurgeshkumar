package com.restaturantService.Service;

import com.restaturantService.dto.MenuItemRequest;
import com.restaturantService.dto.MenuItemResponse;
import com.restaturantService.entity.Restaurant;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface MenuItemService {

    MenuItemResponse create(MenuItemRequest request, Integer restaurantId);

    MenuItemResponse update(Integer id, MenuItemRequest request);

    MenuItemResponse get(Integer id);

    List<MenuItemResponse> getAll();

    void delete(Integer id);

    void deleteAll();

    Page<MenuItemResponse> findAllMenu(Integer page
            , Integer size
            , String sortBy
            , String sorting);
}
