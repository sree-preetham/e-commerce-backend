package com.sreepreetham.inventory_service.service;

import com.sreepreetham.inventory_service.entity.Inventory;
import com.sreepreetham.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  @Transactional(readOnly = true)
  public boolean isInStock(String skuCode) {
    Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);
    return inventory.isPresent();
  }
}
