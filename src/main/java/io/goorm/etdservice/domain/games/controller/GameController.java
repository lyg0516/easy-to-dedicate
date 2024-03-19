package io.goorm.etdservice.domain.games.controller;


import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-prefix}/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public ResponseEntity getGames() {
        //TODO 공용 Response Dto 형태 만들기
        List<GameDto> games = gameService.getGames();
        return ResponseEntity.ok().body(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity getGame() {
        return null;
    }

    @PostMapping()
    public ResponseEntity createGame(@RequestBody() GameDto dto) {

        //TODO Game Image MultipartFile 업로드 기능 추가 필요

        Long gameId = gameService.createGames(dto);
        return ResponseEntity.ok().body(gameId);
    }


}
