package com.ecommerce.notificationservice.kafka;

import com.ecommerce.notificationservice.model.Notification;
import com.ecommerce.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final NotificationRepository repository;

    public NotificationConsumer(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "payment-events", groupId = "notification-service")
    public void consumePaymentEvent(String message) {
        System.out.println("Received payment event: " + message);

        Notification notification = new Notification();
        notification.setMessage(message);
        repository.save(notification);
    }
}