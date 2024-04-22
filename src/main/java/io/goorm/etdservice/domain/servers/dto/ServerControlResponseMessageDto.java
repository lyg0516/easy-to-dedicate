package io.goorm.etdservice.domain.servers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class ServerControlResponseMessageDto {

    private String type;

    @JsonProperty("server_id")
    private UUID serverId;
    private String status;

    @JsonProperty("server_control_id")
    private Long serverControlId;
    private String host;
    private Integer port;
    private String message;

    @Builder
    public ServerControlResponseMessageDto(String type, UUID serverId, String status, Long serverControlId, String host, Integer port, String message) {
        this.type = type;
        this.serverId = serverId;
        this.status = status;
        this.serverControlId = serverControlId;
        this.host = host;
        this.port = port;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServerControlResponseMessageDto{" +
                "type='" + type + '\'' +
                ", serverId=" + serverId +
                ", status='" + status + '\'' +
                ", serverControlId=" + serverControlId +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", message='" + message + '\'' +
                '}';
    }
}
