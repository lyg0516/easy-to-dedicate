package io.goorm.etdservice.domain.servers.service;

import io.goorm.etdservice.domain.common.types.GameType;
import io.goorm.etdservice.domain.games.entity.*;
import io.goorm.etdservice.domain.games.repository.GameRepository;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.members.MemberRepository;
import io.goorm.etdservice.domain.servers.dto.ServerControlMessageDto;
import io.goorm.etdservice.domain.servers.dto.ServerOptionDto;
import io.goorm.etdservice.domain.servers.dto.vo.SystemData;
import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.entity.ServerControl;
import io.goorm.etdservice.domain.servers.repository.ServerControlRepository;
import io.goorm.etdservice.domain.servers.repository.ServerRepository;
import io.goorm.etdservice.domain.servers.types.ControlType;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import io.goorm.etdservice.global.message.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServerService {

    private final ServerRepository serverRepository;
    private final ServerControlRepository serverControlRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;
    private final RabbitMQProducer rabbitMQProducer;
    private final GameOptionRepository gameOptionRepository;

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

        ServerControlMessageDto<?> serverControlMessageDto = null;
        // TODO MQ 퍼블리싱
        if(server.getGame().getName().equals("Palworld")){
            serverControlMessageDto = createInitPalworldServerOption(dto, server, control);
        } else if(server.getGame().getName().equals("Enshrouded")){
            serverControlMessageDto = createInitEnshroudedServerOption(dto, server, control);
        }

        rabbitMQProducer.sendMessage(serverControlMessageDto);
        return savedServer.getId();

    }



    private ServerControlMessageDto<?> createInitEnshroudedServerOption(ServerOptionDto dto, Server server, ServerControl control) {
        EnshroudedOption gameOption = new EnshroudedOption(dto.getSlot());
        ServerControlMessageDto<?> serverControlMessageDto = ServerControlMessageDto.builder()
                .game(server.getGame().getName())
                .controlType("create")
                .serverId(server.getId())
                .serverControlId(control.getId())
                .systemData(new SystemData(server.getCpu(), server.getRam()))
                .gameOption(gameOption)
                .build();
        GameOption<?> gameOptionEntity = GameOption.builder()
                .serverID(server.getId())
                .gameOption(gameOption)
                .build();
        gameOptionRepository.save(gameOptionEntity);
        return serverControlMessageDto;
    }

    private ServerControlMessageDto<?> createInitPalworldServerOption(ServerOptionDto dto, Server server, ServerControl control) {
        PalworldOption gameOption = new PalworldOption(dto.getSlot());
        ServerControlMessageDto<?> serverControlMessageDto = ServerControlMessageDto.builder()
                .game(server.getGame().getName())
                .controlType("create")
                .serverId(server.getId())
                .serverControlId(control.getId())
                .systemData(new SystemData(server.getCpu(), server.getRam()))
                .gameOption(gameOption)
                .build();
        GameOption<?> gameOptionEntity = GameOption.builder()
                .serverID(server.getId())
                .gameOption(gameOption)
                .build();
        gameOptionRepository.save(gameOptionEntity);
        return serverControlMessageDto;
    }

    @SneakyThrows
    public void restartServer(UUID serverId) {

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버입니다."));

        //TODO 게임 옵션 데이터 조회
        //TODO 서버 컨트롤 요청 명세 데이터화
        //TODO MQ Publishing

        ServerControl control = ServerControl.builder()
                .server(server)
                .control(ControlType.RESTART)
//                .appliedAt()  // TODO 생성이 완료 된 후 적용할 것인지에 대한 부분
                .build();
        GameOption gameOption = gameOptionRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버입니다."));
        ServerControlMessageDto<?> serverControlMessageDto = ServerControlMessageDto.builder()
                .game(server.getGame().getName())
                .controlType("restart")
                .serverId(server.getId())
                .serverControlId(control.getId())
                .systemData(new SystemData(server.getCpu(), server.getRam()))
                .gameOption(gameOption)
                .build();
        // TODO Game Deploy 게임서버 재시작 요청
        rabbitMQProducer.sendMessage(serverControlMessageDto);

    }

    @SneakyThrows
    public void deleteServer(UUID serverId) {
        // TODO Game Deploy 게임서버 삭제 요청
        // TODO 삭제는 게임 서버 유지 기간 체크 후 자동 삭제를 기본으로 한다.
        // TODO API 존재 이유는 환불, 기타 문제에 대처하기 위한 API 이다.

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 게임입니다.."));

        //TODO 서버 컨트롤 요청 명세 데이터화
        //TODO MQ Publishing

        ServerControl.builder()
                .server(server)
                .control(ControlType.STOP)
//                .appliedAt()  // TODO 생성이 완료 된 후 적용할 것인지에 대한 부분
                .build();
        ServerControlMessageDto<?> serverControlMessageDto = ServerControlMessageDto.builder()
                .game(server.getGame().getName())
                .controlType("delete")
                .serverId(server.getId())
                .build();
        rabbitMQProducer.sendMessage(serverControlMessageDto);
    }

    @SneakyThrows
    public List<ServerOptionDto> getServers(UUID memberId) {
        // TODO 관리자의 경우 전체 조회
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 유저입니다."));

        List<Server> servers = memberId != null
                ? serverRepository.findByMemberId(memberId)
                : serverRepository.findAll();

        return servers.stream().map(ServerOptionDto::toDto).collect(Collectors.toList());
        // TODO Pagenation 구현
    }

    @SneakyThrows
    public ServerOptionDto getServer(UUID serverId) {
        Server server = serverRepository.findByIdFetchGame(serverId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 서버입니다."));

        return ServerOptionDto.toDto(server);
        // TODO 조회 검증: Member 가 해당 서버를 소유하고 있는게 맞는지 체크
        // TODO 관리자의 경우 패스
        // TODO DTO 반환
    }

    public void requestServerControl() {

    }

    //TODO 서버 컨트롤에 대한 요청 적용 결과
    @SneakyThrows
    public void updateControlResult(Long controlId) {
        ServerControl control = serverControlRepository.findById(controlId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "존재하지 않는 요청입니다."));

        //TODO 적용 완료 시점을 어디로 두어야할지 고민해보기
        //TODO MQ 결과 타입 Enum 으로 만들기 (Deploy 와 상의)
        //TODO MQ 처리 완료에 대한 부분을 가져올수있는지 체크
        control.updateResult(LocalDateTime.now());
    }

    //TODO 서버 현 상태 조회 기능 추가 필요한지?

}
