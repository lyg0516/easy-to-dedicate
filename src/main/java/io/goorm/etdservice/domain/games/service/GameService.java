package io.goorm.etdservice.domain.games.service;

import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.entity.Game;
import io.goorm.etdservice.domain.games.repository.GameRepository;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public List<GameDto> getGames() {
        List<Game> games = gameRepository.findAll(Sort.by(Sort.Direction.DESC,"isActive"));
        return games.stream().map(GameDto::toDto).collect(Collectors.toList());
    }

    public GameDto getGame(Long id) throws DomainException {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임입니다."));
        return GameDto.toDto(game);
    }

    public Long createGames(GameDto dto) {
        Game game = Game.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .imgUrl(dto.getImgUrl())
                .isActive(dto.getIsActive())
                .build();
        gameRepository.save(game);

        return game.getId();
    }


}
