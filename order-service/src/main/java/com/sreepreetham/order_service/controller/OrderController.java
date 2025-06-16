package com.sreepreetham.order_service.controller;

import com.sreepreetham.order_service.dto.OrderDto;
import com.sreepreetham.order_service.form.OrderRequestForm;
import com.sreepreetham.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("")
    ResponseEntity<OrderDto> placeOrder(@RequestBody OrderRequestForm form){
        OrderDto orderDto = orderService.placeOrder(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
}
