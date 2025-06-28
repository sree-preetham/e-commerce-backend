package com.sreepreetham.product_service.controller;

import com.sreepreetham.product_service.dto.ProductDto;
import com.sreepreetham.product_service.dto.ProductsListDto;
import com.sreepreetham.product_service.form.ProductRegistrationForm;
import com.sreepreetham.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
  private final ProductService productService;

  @PostMapping("")
  ResponseEntity<ProductDto> registerProduct(@RequestBody ProductRegistrationForm form) {
    log.info("Received registerProduct request with name: {}", form.getName());
    ProductDto productDto = productService.registerProduct(form);
    return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
  }

  @GetMapping("")
  ResponseEntity<ProductsListDto> getAllProducts() {
    ProductsListDto products = productService.getAllProducts();
    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

  @GetMapping("/{uuid}")
  ResponseEntity<ProductDto> getProductById(@PathVariable("uuid") String uuid) {
    ProductDto productDto = productService.findProductById(uuid);
    return ResponseEntity.status(HttpStatus.OK).body(productDto);
  }
}
