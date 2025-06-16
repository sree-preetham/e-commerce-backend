package com.sreepreetham.inventory_service.controller;

import com.sreepreetham.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;

  @GetMapping("/{skuCode}")
  ResponseEntity<Boolean> isInStock(@PathVariable("skuCode") String skuCode) {
    return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
  }
}
