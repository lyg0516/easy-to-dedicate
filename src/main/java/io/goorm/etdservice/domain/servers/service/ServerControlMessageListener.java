package io.goorm.etdservice.domain.servers.service;

import io.goorm.etdservice.domain.servers.dto.ServerControlResponseMessageDto;
import io.goorm.etdservice.domain.servers.repository.ServerControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServerControlMessageListener {

    private final ServerControlRepository serverControlRepository;

    @RabbitListener(queues = {"etd_consume_queue"})
    public void responseServerControl(ServerControlResponseMessageDto responseMessageDto){
        //TODO response 처리
    }

}
