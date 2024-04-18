package io.goorm.etdservice.global.message.config;

public class RabbitMQConstant {

    static public String ProduceQueueName = "server_request_queue";
    static public String ProduceExchangeName = "server_request_exchange";
//    static public String ProduceRoutingKey = "server_request_key";
    static public String ProduceRoutingKey = "route.*";

    static public String ConsumeQueueName = "server_response_queue";
    static public String ConsumeExchangeName = "server_response_exchange";
    static public String ConsumeRoutingKey = "server_response_key";
}
