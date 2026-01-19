package com.restaturantService.repository;

import com.restaturantService.dto.CustomerResponse;
import com.restaturantService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
        Optional<Customer> findByIdAndIsDeletedFalse(Integer id);

        List<Customer> findAllByIsDeletedFalse();
}
