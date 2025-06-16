package com.sreepreetham.product_service.repository;

import com.sreepreetham.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {

}
