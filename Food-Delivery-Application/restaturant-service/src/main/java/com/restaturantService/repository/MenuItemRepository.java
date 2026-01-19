package com.restaturantService.repository;

import com.restaturantService.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    Optional<MenuItem> findByIdAndIsDeletedFalse(Integer id);
    List<MenuItem> findAllByIsDeletedFalse();
}
