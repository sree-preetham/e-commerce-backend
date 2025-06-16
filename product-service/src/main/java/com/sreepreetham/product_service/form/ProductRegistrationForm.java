package com.sreepreetham.product_service.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRegistrationForm {

  @NotBlank private String name;
  @NotBlank private String description;
  @NotBlank private BigDecimal price;
  @Builder.Default private double rating = 0.0;
  @NotBlank private String category;
  @Builder.Default private Boolean inStock = true;
}
