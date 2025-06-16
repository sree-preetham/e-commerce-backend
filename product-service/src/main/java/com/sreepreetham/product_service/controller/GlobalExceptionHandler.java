package com.sreepreetham.product_service.controller;

import com.sreepreetham.product_service.exception.ProductNotFoundException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException exception) {
    return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
    String paramName = ex.getName();
    String expectedType =
        ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
    String actualValue = ex.getValue() != null ? ex.getValue().toString() : "null";

    Map<String, Object> body =
        Map.of(
            "timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            "status", HttpStatus.BAD_REQUEST.value(),
            "error", "Bad Request",
            "message",
                String.format(
                    "Invalid value '%s' for parameter '%s'. Expected type: %s.",
                    actualValue, paramName, expectedType),
            "path", request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }
}
