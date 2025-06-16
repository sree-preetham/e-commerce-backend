package com.sreepreetham.product_service.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String exceptionMessage) {
    super(exceptionMessage);
  }
}
