package com.restaturantService.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantResponse {
    Integer id;
    private String name;
    private String location;
}
