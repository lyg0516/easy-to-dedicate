package io.goorm.etdservice.domain.servers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class ServerControlResponseMessageDto {

    private String type;
    private UUID serverId;
    private String status;
    private Long serverControlId;
    private String host;
    private Integer port;

    @Builder

    public ServerControlResponseMessageDto(String type, UUID serverId, String status, Long serverControlId, String host, Integer port) {
        this.type = type;
        this.serverId = serverId;
        this.status = status;
        this.serverControlId = serverControlId;
        this.host = host;
        this.port = port;
    }
}
