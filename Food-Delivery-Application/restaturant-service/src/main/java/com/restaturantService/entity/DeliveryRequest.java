package com.restaturantService.entity;


import com.restaturantService.dto.DeliveryStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class DeliveryRequest {
    private int orderId;
    private LocalDate deliveryTime;
    private DeliveryStatus deliveryStatus;
}
