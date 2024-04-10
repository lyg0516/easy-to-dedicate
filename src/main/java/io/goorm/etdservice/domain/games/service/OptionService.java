package io.goorm.etdservice.domain.games.service;


import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.dto.GameOptionDto;
import io.goorm.etdservice.domain.games.entity.EnshroudedOption;
import io.goorm.etdservice.domain.games.entity.GameOption;
import io.goorm.etdservice.domain.games.entity.GameOptionRepository;
import io.goorm.etdservice.domain.games.entity.PalworldOption;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
// 클래스 명칭 고민해보기.
public class OptionService {

    private final GameOptionRepository gameOptionRepository;

    public <T> GameOption<T> updateOption(UUID id, T option){
        return gameOptionRepository.save(new GameOption(id, option));
    }

    public  GameOption queryOption(UUID id) throws DomainException {
        GameOption gameOption = gameOptionRepository.findById(id)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "게임 옵션이 존재하지 않습니다."));
        return gameOption;
    }

    public GameOptionDto resetPalworldOption(UUID serverId) throws DomainException {
        GameOption savedGameOption = gameOptionRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임서버입니다."));
        PalworldOption resetOption = new PalworldOption(((PalworldOption) savedGameOption.getGameOption()).getPLAYERS());
        GameOption optionEntity = gameOptionRepository.save(new GameOption(serverId, resetOption));
        return new GameOptionDto(optionEntity.getServerID(), optionEntity.getGameOption());
    }

    public GameOptionDto resetEnshroudedOption(UUID serverId) throws DomainException {
        GameOption savedGameOption = gameOptionRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임서버입니다."));
        EnshroudedOption resetOption = new EnshroudedOption(((EnshroudedOption) savedGameOption.getGameOption()).getSLOT_COUNT());
        GameOption optionEntity = gameOptionRepository.save(new GameOption(serverId, resetOption));
        return new GameOptionDto(optionEntity.getServerID(), optionEntity.getGameOption());
    }

    public void deleteOption(UUID id) {
        gameOptionRepository.deleteById(id);
    }

    // 게임 서버 생성 옵션 적용 시
    // 기본 게임 환경 옵션 자동 생성
    // 서버 Pod 을 만들기 위한 데이터 폼을 만들어야 하는가??

}
