package com.sreepreetham.inventory_service.service;

import com.sreepreetham.inventory_service.dto.InventoryResponse;
import com.sreepreetham.inventory_service.entity.Inventory;
import com.sreepreetham.inventory_service.repository.InventoryRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  @Transactional(readOnly = true)
  public List<InventoryResponse> isInStock(List<String> skuCode) {
    List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCode);
    Map<String, Inventory> inventoryMap =
        inventoryList.stream().collect(Collectors.toMap(Inventory::getSkuCode, inv -> inv));

    return skuCode.stream()
        .map(
            sku -> {
              Inventory inv = inventoryMap.get(sku);
              return InventoryResponse.builder()
                  .skuCode(sku)
                  .isInStock(inv != null && inv.getQuantity() > 0)
                  .build();
            })
        .toList();
  }
}
