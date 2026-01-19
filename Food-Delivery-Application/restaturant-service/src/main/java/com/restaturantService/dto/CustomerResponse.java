package com.restaturantService.dto;

import com.restaturantService.entity.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CustomerResponse {
    private Integer id;

    private String name;
    private String email;

}
