package com.sreepreetham.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private String skuCode;
  private Integer quantity;
}
