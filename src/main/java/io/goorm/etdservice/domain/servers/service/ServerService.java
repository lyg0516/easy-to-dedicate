package io.goorm.etdservice.domain.servers.service;


import io.goorm.etdservice.domain.servers.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServerService {

    private final ServerRepository serverRepository;

    public void createServer() {

    }

    public void restartServer() {

    }

    public void deleteServer(UUID serverId) {

    }

    public void getServers(UUID userId) {
        if (userId == null) {

        }
        // TODO Pagenation 구현
    }

    public void getServer(UUID serverId) {
        serverRepository.findById(serverId);
    }



}
