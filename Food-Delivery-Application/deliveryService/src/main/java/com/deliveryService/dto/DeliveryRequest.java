package com.deliveryService.dto;

import com.deliveryService.enums.DeliveryStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeliveryRequest {
    int orderId;
    LocalDate deliveryTime;
    DeliveryStatus deliveryStatus;
}
