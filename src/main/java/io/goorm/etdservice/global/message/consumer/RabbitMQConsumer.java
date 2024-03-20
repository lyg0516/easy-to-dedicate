package io.goorm.etdservice.global.message.consumer;

import io.goorm.etdservice.global.message.config.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"etd_consume_queue"})
    public void consume(Object object){
        LOGGER.info(String.format("Received message -> %s", object.toString()));
    }
}
