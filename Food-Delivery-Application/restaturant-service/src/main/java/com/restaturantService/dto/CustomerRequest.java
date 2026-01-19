package com.restaturantService.dto;

import com.restaturantService.entity.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRequest {
    private String name;
    private String email;
}
