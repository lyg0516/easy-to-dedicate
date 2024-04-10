package io.goorm.etdservice.domain.servers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class ServerControlResponseMessageDto {

    private  String type;
    private UUID serverId;
    private Long serverControlId;
    private String ipPort;

    @Builder
    public ServerControlResponseMessageDto(String type, UUID serverId, Long serverControlId, String ipPort) {
        this.type = type;
        this.serverId = serverId;
        this.serverControlId = serverControlId;
        this.ipPort = ipPort;
    }
}
