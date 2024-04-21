package io.goorm.etdservice.domain.servers.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.servers.entity.ServerControl;
import io.goorm.etdservice.domain.servers.types.ControlType;
import io.goorm.etdservice.domain.servers.types.ProgressType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerControlDto {
    private Long id;
    private ControlType control;
    private ProgressType progress;
    private String resultMessage;
    private LocalDateTime appliedAt;
    private LocalDateTime createdAt;


    @Builder
    public ServerControlDto(Long id, ControlType control, ProgressType progress, String resultMessage, LocalDateTime appliedAt, LocalDateTime createdAt) {
        this.id = id;
        this.control = control;
        this.progress = progress;
        this.resultMessage = resultMessage;
        this.appliedAt = appliedAt;
        this.createdAt = createdAt;
    }

    public static ServerControlDto toDto(ServerControl entity) {
        return ServerControlDto.builder()
                .id(entity.getId())
                .control(entity.getControl())
                .progress(entity.getProgress())
                .resultMessage(entity.getResultMessage())
                .appliedAt(entity.getAppliedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
