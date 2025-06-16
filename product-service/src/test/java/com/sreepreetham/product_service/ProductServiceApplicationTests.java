package com.sreepreetham.product_service;

import com.sreepreetham.product_service.dto.ProductsListDto;
import com.sreepreetham.product_service.entity.Product;
import com.sreepreetham.product_service.form.ProductRegistrationForm;
import com.sreepreetham.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private ProductRepository productRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void shouldCreateProduct() throws Exception {
    productRepository.deleteAll();
    ProductRegistrationForm form = getProductForm();
    // the content method only takes string, so we need to convert the above object to string
    String formString = objectMapper.writeValueAsString(form);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formString))
        .andExpect(status().isCreated());
    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  @Test
  void getAllProducts() throws Exception {
    productRepository.deleteAll();
    ProductRegistrationForm form = getProductForm();
    Product savedProduct =
        productRepository.save(
            Product.builder()
                .id(UUID.randomUUID())
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .rating(form.getRating())
                .inStock(form.getInStock())
                .category(form.getCategory())
                .build());
    // When
    String responseContent =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/products"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    // Then: Convert JSON to DTO and assert
    ProductsListDto productsListDto =
        objectMapper.readValue(responseContent, ProductsListDto.class);
    Assertions.assertEquals(1, productsListDto.getProductsList().size());
    Assertions.assertEquals("iphone 13", savedProduct.getName());
    Assertions.assertEquals("Apple phone", savedProduct.getDescription());
    Assertions.assertEquals("electronics", savedProduct.getCategory());
    Assertions.assertTrue(savedProduct.getInStock());
    Assertions.assertEquals(4.5, savedProduct.getRating());
    Assertions.assertEquals(BigDecimal.valueOf(100000), savedProduct.getPrice());
  }

  private ProductRegistrationForm getProductForm() {
    return ProductRegistrationForm.builder()
        .name("iphone 13")
        .description("Apple phone")
        .price(BigDecimal.valueOf(100000))
        .rating(4.5)
        .inStock(true)
        .category("electronics")
        .build();
  }
}
