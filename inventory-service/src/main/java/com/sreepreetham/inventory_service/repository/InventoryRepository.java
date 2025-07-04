package com.sreepreetham.inventory_service.repository;

import com.sreepreetham.inventory_service.entity.Inventory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  Optional<Inventory> findBySkuCode(String skuCode);

  List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
