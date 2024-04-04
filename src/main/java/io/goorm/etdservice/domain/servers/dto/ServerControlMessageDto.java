package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.servers.dto.vo.SystemData;
import lombok.Builder;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerControlMessageDto<T> {
    //TODO 메시지 포맷 변경
    private String game;
    private String controlType;
    private UUID serverId;
    private String containerImage;
    private Long serverControlId;
    private SystemData systemData;
    private T gameOption;

    @Builder
    public ServerControlMessageDto(String game, String controlType, UUID serverId, String containerImage, Long serverControlId,T gameOption, SystemData systemData) {
        this.game = game;
        this.controlType = controlType;
        this.serverId = serverId;
        this.containerImage = containerImage;
        this.serverControlId = serverControlId;
        this.gameOption = gameOption;
        this.systemData = systemData;
    }
}
