package com.restaturantService.dto;

import com.restaturantService.entity.Customer;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private LocalDateTime orderDate;
}
