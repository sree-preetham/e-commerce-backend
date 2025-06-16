package com.sreepreetham.product_service.service;

import com.sreepreetham.product_service.dto.ProductDto;
import com.sreepreetham.product_service.dto.ProductsListDto;
import com.sreepreetham.product_service.entity.Product;
import com.sreepreetham.product_service.exception.ProductNotFoundException;
import com.sreepreetham.product_service.form.ProductRegistrationForm;
import com.sreepreetham.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository productRepository;

  public ProductDto registerProduct(ProductRegistrationForm form) {
    Product product =
        Product.builder()
            .name(form.getName())
            .description(form.getDescription())
            .id(UUID.randomUUID())
            .price(form.getPrice())
            .rating(form.getRating())
            .category(form.getCategory())
            .inStock(form.getInStock())
            .build();
    productRepository.save(product);
    log.info("Product {} is saved", product.getId());
    return new ProductDto(product);
  }

  public ProductsListDto getAllProducts() {
    List<Product> products = productRepository.findAll();
    ProductsListDto productsListDto = new ProductsListDto();
    for (Product product : products) {
      productsListDto.getProductsList().add(new ProductDto(product));
    }
    return productsListDto;
  }

  public ProductDto findProductById(UUID uuid) {
    Product product =
        productRepository
            .findById(uuid)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + uuid));
    return new ProductDto(product);
  }
}
