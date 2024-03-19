package io.goorm.etdservice.domain.games.controller;


import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity createGame() {
        return null;
    }


}
