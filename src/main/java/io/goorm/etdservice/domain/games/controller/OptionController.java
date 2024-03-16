package io.goorm.etdservice.domain.games.controller;


import io.goorm.etdservice.domain.games.dto.EnshroudedOptionDto;
import io.goorm.etdservice.domain.games.dto.PalworldOptionDto;
import io.goorm.etdservice.domain.games.dto.ServerOptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/options")
@RequiredArgsConstructor
@Slf4j
public class OptionController {


    // 서버 생성에 대한 옵션 부분
    @PostMapping("/server")
    public ResponseEntity createServerOption(@RequestBody() ServerOptionDto option) {
        return null;
    }

    @PutMapping("/server/{id}")
    public ResponseEntity updateServerOption(@PathVariable UUID id, @RequestBody() ServerOptionDto option) {
        return null;
    }


    @PutMapping("/server/{id}/palworld")
    public ResponseEntity updatePalworldOption(@PathVariable UUID id, @RequestBody() PalworldOptionDto option) {
        return null;
    }

    @PutMapping("/server/{id}/enshrouded")
    public ResponseEntity updateEnshroudedOption(@PathVariable UUID id, @RequestBody() EnshroudedOptionDto option) {
        return null;
    }


}
