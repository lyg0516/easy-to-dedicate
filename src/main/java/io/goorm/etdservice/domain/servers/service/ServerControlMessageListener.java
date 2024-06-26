package io.goorm.etdservice.domain.servers.service;

import io.goorm.etdservice.domain.servers.dto.ServerControlResponseMessageDto;
import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.entity.ServerControl;
import io.goorm.etdservice.domain.servers.repository.ServerControlRepository;
import io.goorm.etdservice.domain.servers.repository.ServerRepository;
import io.goorm.etdservice.domain.servers.types.ProgressType;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ServerControlMessageListener {

    private final ServerControlRepository serverControlRepository;
    private final ServerRepository serverRepository;

    @RabbitListener(queues = {"server_response_queue"})
    @Transactional
    public void responseServerControl(ServerControlResponseMessageDto responseMessageDto) throws DomainException {
        //TODO response 처리
        log.info("message: {} " , responseMessageDto);
        if(responseMessageDto.getServerId() == null || responseMessageDto.getServerControlId() == null)
            throw new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버 입니다.");
        UUID serverId = responseMessageDto.getServerId();
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버 입니다."));
        if(responseMessageDto.getHost() != null && responseMessageDto.getPort() != null) {
            server.setServerHost(responseMessageDto.getHost(), responseMessageDto.getPort());
        }
        ServerControl serverControl = serverControlRepository.findById(responseMessageDto.getServerControlId())
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "유효하지 않는 아이디 입니다."));
        serverControl.updateResult(LocalDateTime.now(), ProgressType.valueOfLabel(responseMessageDto.getStatus()), responseMessageDto.getMessage());
    }

}
