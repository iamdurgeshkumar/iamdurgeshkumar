package com.deliveryService.entity;


import com.deliveryService.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "delivery")
public class Delivery {

    @Id
    private String id;
    private Long orderId;
    private LocalDate deliveryTime;
    private DeliveryStatus deliveryStatus;
    boolean isDeleted = false;

}
