package com.sreepreetham.order_service.service;

import com.sreepreetham.order_service.dto.InventoryResponse;
import com.sreepreetham.order_service.dto.OrderDto;
import com.sreepreetham.order_service.dto.OrderLineItemsDto;
import com.sreepreetham.order_service.entity.Order;
import com.sreepreetham.order_service.entity.OrderLineItems;
import com.sreepreetham.order_service.form.OrderRequestForm;
import com.sreepreetham.order_service.repository.OrderRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;

  public OrderDto placeOrder(OrderRequestForm form) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItemsList =
        form.getOrderLineItemsDtoList().stream().map(this::dtoToEntity).toList();
    order.setOrderLineItemsList(orderLineItemsList);
    List<String> skuCodes =
        order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
    // Call inventory service to place the order if the product is in stock
    InventoryResponse[] inventoryResponseArray =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://inventory-service/api/inventory",
                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();

    assert inventoryResponseArray != null;
    boolean allProductsInStock =
        Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
    if (allProductsInStock) orderRepository.save(order);
    else throw new IllegalArgumentException("Product is not in stock");
    return new OrderDto(order, form.getOrderLineItemsDtoList());
  }

  private OrderLineItems dtoToEntity(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    return orderLineItems;
  }
}
