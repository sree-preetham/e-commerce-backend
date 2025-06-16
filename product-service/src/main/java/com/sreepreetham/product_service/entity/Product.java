package com.sreepreetham.product_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "products")
public class Product {
  @Id private UUID id;
  private String name;
  private String description;
  private BigDecimal price;
  private double rating;
  private String category;
  private Boolean inStock;
}
