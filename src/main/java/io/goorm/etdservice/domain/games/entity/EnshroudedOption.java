package io.goorm.etdservice.domain.games.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class EnshroudedOption {


    private String SERVER_NAME = "My Enshrouded Server"; // Optional, Name of the server
    private String PASSWORD = "password"; // Optional, Password for the server
    private String SAVE_DIRECTORY= "./savegame"; // Optional, Save directory for the game
    private String LOG_DIRECTORY= "./logs"; // Optional, Log directory for the server
    private String SERVER_IP= "0.0.0.0"; // Optional, IP address for the server
    private Integer GAME_PORT = 15636; // Optional, Game port for the server
    private Integer QUERY_PORT = 15637; // Optional, Query port for the server
    private Integer SLOT_COUNT = 16; // Optional, Number of slots for the server

    @Builder
    public EnshroudedOption(String SERVER_NAME, String PASSWORD, String SAVE_DIRECTORY, String LOG_DIRECTORY, String SERVER_IP, Integer GAME_PORT, Integer QUERY_PORT, Integer SLOT_COUNT) {
        this.SERVER_NAME = SERVER_NAME;
        this.PASSWORD = PASSWORD;
        this.SAVE_DIRECTORY = SAVE_DIRECTORY;
        this.LOG_DIRECTORY = LOG_DIRECTORY;
        this.SERVER_IP = SERVER_IP;
        this.GAME_PORT = GAME_PORT;
        this.QUERY_PORT = QUERY_PORT;
        this.SLOT_COUNT = SLOT_COUNT;
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

    public EnshroudedOption(int slot){
        this.SLOT_COUNT = slot;
    }

}
