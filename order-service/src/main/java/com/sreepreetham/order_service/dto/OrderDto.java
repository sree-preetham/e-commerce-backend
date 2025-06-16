package com.sreepreetham.order_service.dto;

import com.sreepreetham.order_service.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UUID id;
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsList;

    public OrderDto(Order order, List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.orderLineItemsList = orderLineItemsDtoList;
    }
}
