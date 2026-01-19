package com.restaturantService.repository;

import com.restaturantService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByIdAndIsDeletedIsFalse(Integer id);

    List<Order> findAllByIsDeletedFalse();

}

