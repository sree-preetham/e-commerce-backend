package com.sreepreetham.order_service.form;

import com.sreepreetham.order_service.dto.OrderLineItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestForm {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
