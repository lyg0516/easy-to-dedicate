package io.goorm.etdservice.global.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue consumeQueue() { return new Queue(RabbitMQConstant.ConsumeQueueName);}

    @Bean
    public TopicExchange consumeExchange() { return new TopicExchange(RabbitMQConstant.ConsumeExchangeName);}

    @Bean
    public Binding consumeBinding() {
        return BindingBuilder.bind(consumeQueue())
                .to(consumeExchange())
                .with(RabbitMQConstant.ConsumeRoutingKey);
    }
    @Bean
    public Queue produceQueue() { return new Queue(RabbitMQConstant.ProduceQueueName);}

    @Bean
    public TopicExchange produceExchange() { return new TopicExchange(RabbitMQConstant.ProduceExchangeName);}

    @Bean
    public Binding produceBinding() {
        return BindingBuilder.bind(produceQueue())
                .to(produceExchange())
                .with(RabbitMQConstant.ProduceRoutingKey);
    }
}
