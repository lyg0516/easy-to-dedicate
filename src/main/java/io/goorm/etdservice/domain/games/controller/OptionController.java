package io.goorm.etdservice.domain.games.controller;


import io.goorm.etdservice.domain.games.dto.EnshroudedOptionDto;
import io.goorm.etdservice.domain.games.dto.GameOptionDto;
import io.goorm.etdservice.domain.games.dto.PalworldOptionDto;
import io.goorm.etdservice.domain.games.entity.EnshroudedOption;
import io.goorm.etdservice.domain.games.entity.GameOption;
import io.goorm.etdservice.domain.games.entity.PalworldOption;
import io.goorm.etdservice.domain.games.service.OptionService;
import io.goorm.etdservice.global.exception.DomainException;
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

    private final OptionService optionService;

    @PutMapping("/palworld/reset/{id}")
    public ResponseEntity resetPalworldOption(@PathVariable UUID id) throws DomainException {
        GameOptionDto gameOptionDto = optionService.resetPalworldOption(id);
        return ResponseEntity.ok().body(gameOptionDto);
    }

    @PutMapping("/enshrouded/reset/{id}")
    public ResponseEntity resetEnshroudedOption(@PathVariable UUID id) throws DomainException {
        GameOptionDto gameOptionDto = optionService.resetEnshroudedOption(id);
        return ResponseEntity.ok().body(gameOptionDto);
    }

    @PutMapping("/palworld/{id}")
    public ResponseEntity updatePalworldOption(@PathVariable UUID id, @RequestBody() PalworldOption option) {
        GameOption<PalworldOption> palworldOptionGameOption = optionService.updateOption(id, option);
        GameOptionDto<PalworldOption> palworldOptionGameOptionDto = new GameOptionDto<PalworldOption>(
                palworldOptionGameOption.getServerID(),
                palworldOptionGameOption.getGameOption()
        );
        return ResponseEntity.ok().body(palworldOptionGameOptionDto);
    }

    @PutMapping("/enshrouded/{id}")
    public ResponseEntity updateEnshroudedOption(@PathVariable UUID id, @RequestBody() EnshroudedOption option) {
        GameOption<EnshroudedOption> enshroudedOptionGameOption = optionService.updateOption(id, option);
        GameOptionDto<EnshroudedOption> enshroudedOptionGameOptionDto = new GameOptionDto<EnshroudedOption>(
                enshroudedOptionGameOption.getServerID(),
                enshroudedOptionGameOption.getGameOption()
        );
        return ResponseEntity.ok().body(enshroudedOptionGameOptionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity queryGameOption(@PathVariable UUID id) throws DomainException {
        GameOption gameOptionEntity = optionService.queryOption(id);
        GameOptionDto<?> gameOptionDto = new GameOptionDto(id, gameOptionEntity.getGameOption());
        return ResponseEntity.ok().body(gameOptionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGameOption(@PathVariable UUID id) {
        optionService.deleteOption(id);
        return ResponseEntity.ok().body(id);
    }



}
