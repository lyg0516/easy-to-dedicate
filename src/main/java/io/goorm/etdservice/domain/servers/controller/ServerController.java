package io.goorm.etdservice.domain.servers.controller;

import io.goorm.etdservice.domain.servers.dto.ServerOptionDto;
import io.goorm.etdservice.domain.servers.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/servers")
@RequiredArgsConstructor
@Slf4j
public class ServerController {

    private final ServerService serverService;

    // 서버 조회 API
    @GetMapping("")
    public ResponseEntity getServers(@RequestParam("memberId") UUID memberId, @RequestParam("gameId")Long gameId) {
        return null;
    }

    @GetMapping("/{serverId}")
    public ResponseEntity getServer(@PathVariable UUID serverId) {
        return null;
    }

    // 서버 컨트롤 API
    @PostMapping("")
    public ResponseEntity createServer(@RequestBody() ServerOptionDto option) {
        // TODO 생성 후 Server ID 전달
        // TODO 전달된 ID 를 통해 조회 진행
        return null;
    }

    @GetMapping("/{serverId}/restart")
    public ResponseEntity restartServer(@PathVariable UUID serverId) {
        // TODO 재시작에 파트
        // TODO 요청 성공 여부만 전달.
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{serverId}")
    public ResponseEntity deleteServer(@PathVariable UUID serverId) {
        // TODO 삭제 로직
        return ResponseEntity.noContent().build();
    }


}
