package com.sreepreetham.inventory_service.repository;

import com.sreepreetham.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  Optional<Inventory> findBySkuCode(String skuCode);
}
