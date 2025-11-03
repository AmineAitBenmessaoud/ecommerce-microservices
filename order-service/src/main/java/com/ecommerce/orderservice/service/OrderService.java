package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.event.OrderPlacedEvent;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .status("CREATED")
                .build();

        orderRepository.save(order);

        // Publish event to Kafka
        OrderPlacedEvent event = new OrderPlacedEvent(
                order.getOrderNumber(),
                order.getProductId(),
                order.getQuantity(),
                order.getPrice()
        );

        kafkaTemplate.send("order-events", event);

        return order;
    }
}