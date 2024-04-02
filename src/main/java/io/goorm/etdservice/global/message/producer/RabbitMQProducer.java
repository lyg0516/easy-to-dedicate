package io.goorm.etdservice.global.message.producer;

import io.goorm.etdservice.global.message.config.RabbitMQConstant;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Object object){
        LOGGER.info(String.format("Message send -> %s", object.toString()));
        rabbitTemplate.convertAndSend(RabbitMQConstant.ProduceExchangeName, RabbitMQConstant.ProduceRoutingKey, object);
    }
}
