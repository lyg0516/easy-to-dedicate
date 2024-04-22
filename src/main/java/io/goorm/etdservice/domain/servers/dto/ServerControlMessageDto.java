package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.servers.dto.vo.SystemData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerControlMessageDto<T> {

    private String game;

    @JsonProperty("type")
    private String controlType;

    private UUID serverId;

    @JsonProperty("image")
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
