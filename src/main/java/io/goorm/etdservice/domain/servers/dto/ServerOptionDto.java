package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.types.GameType;
import io.goorm.etdservice.domain.common.types.TermType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerOptionDto {

    private GameType game;
    private TermType term = TermType.FIXED;     // 고정 서버 형태
    private Integer slot;            // Player Or Ram Slot
    private String location;        // Create Location
    private Integer days = 0;       // for Activate Duration;

    private LocalDateTime requestAt;
    private LocalDateTime confirmAt;

}
