package io.goorm.etdservice.global.message.producer;

import io.goorm.etdservice.global.message.config.RabbitMQConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendServerControlMessage(UUID clusterId, Object object){
        String route = RabbitMQConstant.ProduceRoutingKey.replace("*",clusterId.toString());
        log.info(String.format("Message send -> %s | %s", route, object.toString()));
        rabbitTemplate.convertAndSend(RabbitMQConstant.ProduceExchangeName, route, object);
    }
}
