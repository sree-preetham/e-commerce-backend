package com.sreepreetham.product_service.controller;

import com.sreepreetham.product_service.dto.ProductDto;
import com.sreepreetham.product_service.dto.ProductsListDto;
import com.sreepreetham.product_service.form.ProductRegistrationForm;
import com.sreepreetham.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping("")
  ResponseEntity<ProductDto> registerProduct(@RequestBody ProductRegistrationForm form) {
    ProductDto productDto = productService.registerProduct(form);
    return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
  }

  @GetMapping("")
  ResponseEntity<ProductsListDto> getAllProducts() {
    ProductsListDto products = productService.getAllProducts();
    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

  @GetMapping("/{uuid}")
  ResponseEntity<ProductDto> getProductById(@PathVariable("uuid") UUID uuid) {
    ProductDto productDto = productService.findProductById(uuid);
    return ResponseEntity.status(HttpStatus.OK).body(productDto);
  }
}
