package com.sreepreetham.inventory_service.controller;

import com.sreepreetham.inventory_service.dto.InventoryResponse;
import com.sreepreetham.inventory_service.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;

  /*
   This is a single API call checking for one product whether it is in stock or not
   However a order does not need to have just one product, it can have hundreds of products in single order
   In that case it doesn't make sense to make hundred api calls, therefore we need some way to get the stock for all
   products at once

   @GetMapping("/{skuCode}")
   ResponseEntity<Boolean> isInStock(@PathVariable("skuCode") String skuCode) {
     return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
   }

  */

  // http://localhost:8083/api/inventory/skuCode=iphone-13&skuCode=iphone13-red
  @GetMapping
  ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode) {
    return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
  }
}
