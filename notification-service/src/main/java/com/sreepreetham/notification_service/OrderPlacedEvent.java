package com.sreepreetham.notification_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
}

// It is always recommended to have your own version of these classes when you are sending or receiving payloads.
// However for the class in here the complete className is com.sreepreetham.notification_service.OrderPlacedEvent, wherease for the order service one it is com.sreepreetham.order-service.event.OrderPlacedEvent
// So we need to have some mapping between them, we will configure them in both the services application.properties
