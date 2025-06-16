package com.sreepreetham.product_service.dto;

import com.sreepreetham.product_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
  private UUID id;
  private String name;
  private String description;
  private BigDecimal price;
  private double rating;
  private String category;
  private Boolean inStock;

  public ProductDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = product.getPrice();
    this.rating = product.getRating();
    this.category = product.getCategory();
    this.inStock = product.getInStock();
  }
}
