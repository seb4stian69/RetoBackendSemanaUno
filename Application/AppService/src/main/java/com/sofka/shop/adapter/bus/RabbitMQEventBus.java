package com.sofka.shop.adapter.bus;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.ApplicationConfig;
import com.sofka.shop.GsonEventSerializer;
import com.sofka.shop.generic.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQEventBus implements EventBus {

    private final RabbitTemplate rabbitTemplate;
    private final GsonEventSerializer serializer;
    private final ApplicationEventPublisher applicationEventPublisher;

    public RabbitMQEventBus(RabbitTemplate rabbitTemplate,  GsonEventSerializer serializer, ApplicationEventPublisher applicationEventPublisher) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.serialize(event)
        );
        rabbitTemplate.convertAndSend(
                ApplicationConfig.EXCHANGE, event.type, notification.serialize().getBytes()
        );
        //applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishError(Throwable errorEvent) {
        var event = new ErrorEvent(errorEvent.getClass().getTypeName(), errorEvent.getMessage());
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.serialize(event)
        );
        rabbitTemplate.convertAndSend(ApplicationConfig.EXCHANGE, event.type, notification.serialize().getBytes());
    }


}
