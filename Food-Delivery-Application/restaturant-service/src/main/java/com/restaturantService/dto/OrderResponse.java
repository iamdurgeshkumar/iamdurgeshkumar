package com.restaturantService.dto;

import com.restaturantService.entity.Customer;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Integer id;
    private LocalDateTime orderDate;
}
