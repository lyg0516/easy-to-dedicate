package io.goorm.etdservice.domain.games.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class EnshroudedOptionMessage {


    private String SERVER_NAME = "My Enshrouded Server"; // Optional, Name of the server
    private String PASSWORD = "password"; // Optional, Password for the server
    private String SAVE_DIRECTORY= "./savegame"; // Optional, Save directory for the game
    private String LOG_DIRECTORY= "./logs"; // Optional, Log directory for the server
    private String SERVER_IP= "0.0.0.0"; // Optional, IP address for the server
    private String GAME_PORT;
    private String QUERY_PORT;
    private String SLOT_COUNT;

    @Builder
    public EnshroudedOptionMessage(EnshroudedOption enshroudedOption) {
        this.SERVER_NAME = enshroudedOption.getSERVER_NAME();
        this.PASSWORD = enshroudedOption.getPASSWORD();
        this.SAVE_DIRECTORY = enshroudedOption.getSAVE_DIRECTORY();
        this.LOG_DIRECTORY = enshroudedOption.getSERVER_IP();
        this.SERVER_IP = enshroudedOption.getGAME_PORT().toString();
        this.GAME_PORT = enshroudedOption.getQUERY_PORT().toString();
        this.QUERY_PORT = enshroudedOption.getQUERY_PORT().toString();
        this.SLOT_COUNT = enshroudedOption.getSLOT_COUNT().toString();
    }

    public static EnshroudedOption initEnshroudedOption(Integer slot) {
        return EnshroudedOption.builder()
                .SERVER_NAME(UUID.randomUUID().toString())
                .PASSWORD("password")
                .GAME_PORT(15636)
                .QUERY_PORT(15637)
                .SLOT_COUNT(slot)
                .build();
    }

}
