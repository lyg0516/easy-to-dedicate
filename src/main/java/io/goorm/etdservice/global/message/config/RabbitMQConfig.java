package io.goorm.etdservice.global.message.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
