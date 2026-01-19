package com.deliveryService.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderRequest {
    private LocalDateTime orderDate;

}
