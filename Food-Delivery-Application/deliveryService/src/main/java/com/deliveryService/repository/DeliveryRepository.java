package com.deliveryService.repository;

import com.deliveryService.entity.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    Optional<Delivery> findByIdAndIsDeletedIsFalse(String id);

    List<Delivery> findAllByIsDeletedFalse();
}
