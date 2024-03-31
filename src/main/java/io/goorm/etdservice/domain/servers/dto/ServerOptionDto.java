package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.types.TermType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class ServerOptionDto {

    private UUID id;
    private Long gameId;
    private String game;
    private UUID memberId;
    private TermType term = TermType.FIXED;     // 고정 서버 형태
    private Integer slot;            // Player Or Ram Slot
    private String location;        // Create Location
    private Integer days = 0;       // for Activate Duration;
    private LocalDateTime createdAt;

    @Builder
    public ServerOptionDto(UUID id, Long gameId, String game, UUID memberId, TermType term, Integer slot, String location, Integer days, LocalDateTime createdAt) {
        this.id = id;
        this.gameId = gameId;
        this.game = game;
        this.memberId = memberId;
        this.term = term;
        this.slot = slot;
        this.location = location;
        this.days = days;
        this.createdAt = createdAt;
    }

    public static ServerOptionDto toDto(Server entity) {
        return ServerOptionDto.builder()
                .id(entity.getId())
                .game(entity.getGame().getName())
                .term(entity.getTerm())
                .slot(entity.getSlot())
                .location(entity.getLocation())
                .days(entity.getDays())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
