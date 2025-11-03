package com.ecommerce.paymentservice.kafka;

import com.ecommerce.paymentservice.event.OrderPlacedEvent;
import com.ecommerce.paymentservice.model.Payment;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private final PaymentRepository repository;
    private final PaymentProducer producer;

    public PaymentConsumer(PaymentRepository repository, PaymentProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @KafkaListener(topics = "order-events", groupId = "payment-service")
    public void consumeOrderEvent(OrderPlacedEvent event) {
        System.out.println("Received order event: " + event);

        // Simulate payment
        Payment payment = new Payment();
        payment.setOrderId(event.getOrderNumber());
        payment.setAmount(event.getPrice() * event.getQuantity());
        payment.setStatus("SUCCESS");
        repository.save(payment);

        // Send Kafka event to notification service
        producer.sendPaymentEvent("Payment successful for order " + event.getOrderNumber() + 
                " - Amount: $" + payment.getAmount());
    }
}
