package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.servers.types.TermType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerOptionDto {

    private Long gameId;
    private UUID memberId;
    private TermType term = TermType.FIXED;     // 고정 서버 형태
    private Integer slot;            // Player Or Ram Slot
    private String location;        // Create Location
    private Integer days = 0;       // for Activate Duration;

    private LocalDateTime requestAt;
    private LocalDateTime confirmAt;


    @Builder
    public ServerOptionDto(Long gameId, UUID memberId, TermType term, Integer slot, String location, Integer days, LocalDateTime requestAt, LocalDateTime confirmAt) {
        this.gameId = gameId;
        this.memberId = memberId;
        this.term = term;
        this.slot = slot;
        this.location = location;
        this.days = days;
        this.requestAt = requestAt;
        this.confirmAt = confirmAt;
    }

}
