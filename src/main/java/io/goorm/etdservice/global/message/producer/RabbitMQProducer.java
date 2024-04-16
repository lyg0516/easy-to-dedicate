package io.goorm.etdservice.global.message.producer;

import io.goorm.etdservice.global.message.config.RabbitMQConstant;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @SneakyThrows
    public void sendMessage(Object object){
        log.info("Message send -> {}", object); // 로그를 찍으려면 toString 메소드 또는 직렬화 후 찍힙니다.
        rabbitTemplate.convertAndSend(RabbitMQConstant.ProduceExchangeName, RabbitMQConstant.ProduceRoutingKey, object);
    }
}
