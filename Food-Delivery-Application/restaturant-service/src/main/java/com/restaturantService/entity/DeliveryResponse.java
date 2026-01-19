package com.restaturantService.entity;

import com.restaturantService.dto.DeliveryStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
@Data
public class DeliveryResponse {
        @Id
        String id;

        int orderId;
        LocalDate deliveryTime;
        DeliveryStatus deliveryStatus;
}
