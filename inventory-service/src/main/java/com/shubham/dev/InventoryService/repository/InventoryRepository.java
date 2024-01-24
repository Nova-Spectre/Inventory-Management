package com.shubham.dev.InventoryService.repository;

import com.shubham.dev.InventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Optional<Inventory> findBySkuCode(String SkuCode);
}
