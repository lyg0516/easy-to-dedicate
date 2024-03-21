package io.goorm.etdservice.global.message.config;

public class RabbitMQConstant {

    static public String ProduceQueueName = "etd_produce_queue";
    static public String ProduceExchangeName = "etd_produce_exchange";
    static public String ProduceRoutingKey = "etd_produce_routing_key";

    static public String ConsumeQueueName = "etd_consume_queue";
    static public String ConsumeExchangeName = "etd_consume_exchange";
    static public String ConsumeRoutingKey = "etd_consume_routing_key";
}
