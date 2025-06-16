package com.sreepreetham.order_service.service;

import com.sreepreetham.order_service.dto.OrderDto;
import com.sreepreetham.order_service.dto.OrderLineItemsDto;
import com.sreepreetham.order_service.entity.Order;
import com.sreepreetham.order_service.entity.OrderLineItems;
import com.sreepreetham.order_service.form.OrderRequestForm;
import com.sreepreetham.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderDto placeOrder(OrderRequestForm form){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = form.getOrderLineItemsDtoList().stream().map(this::dtoToEntity).toList();
        order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);
        return new OrderDto(order, form.getOrderLineItemsDtoList());
    }

    private OrderLineItems dtoToEntity(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
