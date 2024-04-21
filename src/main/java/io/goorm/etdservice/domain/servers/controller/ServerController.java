package io.goorm.etdservice.domain.servers.controller;

import io.goorm.etdservice.domain.servers.dto.ServerControlDto;
import io.goorm.etdservice.domain.servers.dto.ServerOptionDto;
import io.goorm.etdservice.domain.servers.service.ServerService;
import io.goorm.etdservice.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/servers")
@RequiredArgsConstructor
@Slf4j
public class ServerController {

    private final ServerService serverService;

    // 서버 조회 API
    @GetMapping("")
    public ResponseEntity getServers(@RequestParam(value = "memberId", required = false) UUID memberId,
                                     @RequestParam(value = "gameId", required = false)Long gameId) {
        List<ServerOptionDto> servers = serverService.getServers(memberId);
        return ResponseEntity.ok().body(servers);
    }

    @GetMapping("/{serverId}")
    public ResponseEntity getServer(@PathVariable UUID serverId) {
        ServerOptionDto server = serverService.getServer(serverId);
        return ResponseEntity.ok().body(server);
    }

    // 서버 컨트롤 API
    @PostMapping("")
    public ResponseEntity createServer(@RequestBody() ServerOptionDto option) throws DomainException {
        // TODO 생성 후 Server ID 전달
        // TODO 전달된 ID 를 통해 조회 진행
        log.info("전달된 옵션 {}",option.toString());
        UUID serverId = serverService.createServer(option);
        return ResponseEntity.created(URI.create(String.format("/%s",serverId.toString()))).build();
    }

    @GetMapping("/{serverId}/restart")
    public ResponseEntity restartServer(@PathVariable UUID serverId) {
        // TODO 재시작에 파트
        // TODO 요청 성공 여부만 전달.
        serverService.restartServer(serverId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{serverId}")
    public ResponseEntity deleteServer(@PathVariable UUID serverId) {
        // TODO 삭제 로직
        serverService.deleteServer(serverId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{serverId}/controls")
    public ResponseEntity getServerControls(@PathVariable UUID serverId) {
        List<ServerControlDto> controls = serverService.getServerControlsByServer(serverId);
        return ResponseEntity.ok().body(controls);
    }


}
