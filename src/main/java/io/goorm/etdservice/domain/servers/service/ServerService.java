package io.goorm.etdservice.domain.servers.service;

import io.goorm.etdservice.domain.games.entity.Game;
import io.goorm.etdservice.domain.games.repository.GameRepository;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.members.MemberRepository;
import io.goorm.etdservice.domain.servers.dto.ServerOptionDto;
import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.entity.ServerControl;
import io.goorm.etdservice.domain.servers.repository.ServerControlRepository;
import io.goorm.etdservice.domain.servers.repository.ServerRepository;
import io.goorm.etdservice.domain.servers.types.ControlType;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServerService {

    private final ServerRepository serverRepository;
    private final ServerControlRepository serverControlRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    public UUID createServer(ServerOptionDto dto) throws DomainException {

        // 데이터 조회 부분
        // TODO 각 엔티티의 서비스 부분으로 변환해야하는지 고민해볼것
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 유저입니다."));

        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임입니다.."));

        // TODO 옵션 별 리소스 설정이 달라야한다. 이 부분을 인터페이스로 별도 구성하면 좋을듯
        // TODO location 은 배포될 위치에 대한 정보이다.
        // TODO 배포 위치는 클러스터 또는 나라, 지역으로 정해진다.

        Integer ram = 8;
        if (game.getName() != "Minecraft") {
            Integer slot = dto.getSlot();
            if (slot <= 8) {
                ram = 8;
            } else if (slot > 8 && slot <= 16) {
                ram = 16;
            } else {
                ram = 32;
            }
        }

        Server server = Server.builder()
                .member(member)
                .game(game)
                .term(dto.getTerm())
                .cpu(4)
                .ram(ram)
                .slot(dto.getSlot())
//                .location()
                .days(dto.getDays())
                .build();

        Server savedServer = serverRepository.save(server);

        ServerControl control = ServerControl.builder()
                .server(savedServer)
                .control(ControlType.CREATE)
//                .appliedAt()  // TODO 생성이 완료 된 후 적용할 것인지에 대한 부분
                .build();

        serverControlRepository.save(control);

        // TODO Game Deploy 게임서버 생성 요청
        // TODO 요청 명세서 작성
        // TODO 게임 별 옵션 데이터 저장

        // TODO MQ 퍼블리싱

        return savedServer.getId();

    }

    public void restartServer(UUID serverId) throws DomainException {

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임입니다.."));

        ServerControl.builder()
                .server(server)
                .control(ControlType.RESTART)
//                .appliedAt()  // TODO 생성이 완료 된 후 적용할 것인지에 대한 부분
                .build();

        // TODO Game Deploy 게임서버 재시작 요청
    }

    public void deleteServer(UUID serverId) throws DomainException {
        // TODO Game Deploy 게임서버 삭제 요청
        // TODO 삭제는 게임 서버 유지 기간 체크 후 자동 삭제를 기본으로 한다.
        // TODO API 존재 이유는 환불, 기타 문제에 대처하기 위한 API 이다.

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임입니다.."));

        ServerControl.builder()
                .server(server)
                .control(ControlType.STOP)
//                .appliedAt()  // TODO 생성이 완료 된 후 적용할 것인지에 대한 부분
                .build();

    }

    public void getServers(UUID memberId) throws DomainException {
        // TODO 관리자의 경우 전체 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 유저입니다."));
        // TODO Pagenation 구현
    }

    public void getServer(UUID serverId) throws DomainException {
        serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버입니다."));
        // TODO 조회 검증: Member 가 해당 서버를 소유하고 있는게 맞는지 체크
        // TODO 관리자의 경우 패스
        // TODO DTO 반환
    }



}
