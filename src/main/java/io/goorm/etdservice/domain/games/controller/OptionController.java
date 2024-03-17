package io.goorm.etdservice.domain.games.controller;


import io.goorm.etdservice.domain.games.dto.EnshroudedOptionDto;
import io.goorm.etdservice.domain.games.dto.PalworldOptionDto;
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


    @PutMapping("/palworld{id}")
    public ResponseEntity updatePalworldOption(@PathVariable UUID id, @RequestBody() PalworldOptionDto option) {
        return null;
    }

    @PutMapping("/enshrouded/{id}")
    public ResponseEntity updateEnshroudedOption(@PathVariable UUID id, @RequestBody() EnshroudedOptionDto option) {
        return null;
    }


}
