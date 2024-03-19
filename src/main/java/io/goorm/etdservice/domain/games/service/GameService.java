package io.goorm.etdservice.domain.games.service;

import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.entity.Game;
import io.goorm.etdservice.domain.games.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public List<GameDto> getGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(GameDto::toDto).collect(Collectors.toList());
    }


}
