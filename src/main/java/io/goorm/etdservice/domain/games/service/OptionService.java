package io.goorm.etdservice.domain.games.service;


import io.goorm.etdservice.domain.games.dto.GameDto;
import io.goorm.etdservice.domain.games.dto.GameOptionDto;
import io.goorm.etdservice.domain.games.entity.EnshroudedOption;
import io.goorm.etdservice.domain.games.entity.GameOption;
import io.goorm.etdservice.domain.games.entity.GameOptionRepository;
import io.goorm.etdservice.domain.games.entity.PalworldOption;
import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.repository.ServerRepository;
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
    private final ServerRepository serverRepository;

    public <T> GameOption<T> updateOption(UUID id, T option){
        return gameOptionRepository.save(new GameOption(id, option));
    }

    public  GameOption queryOption(UUID id) throws DomainException {
        GameOption gameOption = gameOptionRepository.findById(id)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "게임 옵션이 존재하지 않습니다."));
        return gameOption;
    }

    public GameOption resetOption(UUID id) throws DomainException {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "유효하지 않는 서버 ID 입니다."));
        GameOption gameOption = gameOptionRepository.findById(id)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "유효하지 않는 서버 ID 입니다."));
        String gameName = server.getGame().getName();
        GameOption saveOption = null;
        if(gameName == "Palworld"){
            PalworldOption palworldOption = (PalworldOption) gameOption.getGameOption();
            saveOption = gameOptionRepository.save(new GameOption(
                    id, new GameOption(id, new PalworldOption(palworldOption.getPLAYERS())
            )));
        }
        else if(gameName == "Enshrouded"){
            EnshroudedOption enshroudedOption = (EnshroudedOption) gameOption.getGameOption();
            saveOption = gameOptionRepository.save(new GameOption(
                    id, new GameOption(id, new EnshroudedOption(enshroudedOption.getSLOT_COUNT())
            )));
        }
        return saveOption;
    }

    public void deleteOption(UUID id) {
        gameOptionRepository.deleteById(id);
    }

    // 게임 서버 생성 옵션 적용 시
    // 기본 게임 환경 옵션 자동 생성
    // 서버 Pod 을 만들기 위한 데이터 폼을 만들어야 하는가??

}
