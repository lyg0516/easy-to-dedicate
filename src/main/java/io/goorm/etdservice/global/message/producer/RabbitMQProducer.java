package io.goorm.etdservice.global.message.producer;

import io.goorm.etdservice.global.message.config.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendServerControlMessage(UUID clusterId, Object object){
        String route = RabbitMQConstant.ProduceRoutingKey.replace("*",clusterId.toString());
        LOGGER.info(String.format("Message send -> %s | %s", route, object.toString()));
        rabbitTemplate.convertAndSend(RabbitMQConstant.ProduceExchangeName, route, object);
    }
}
