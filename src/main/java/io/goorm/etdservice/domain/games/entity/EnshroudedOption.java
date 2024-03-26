package io.goorm.etdservice.domain.games.entity;



public class EnshroudedOption {

    private String SERVER_NAME = "My Enshrouded Server"; // Optional, Name of the server
    private String PASSWORD = ""; // Optional, Password for the server
    private String SAVE_DIRECTORY= "./savegame"; // Optional, Save directory for the game
    private String LOG_DIRECTORY= "./logs"; // Optional, Log directory for the server
    private String SERVER_IP= "0.0.0.0"; // Optional, IP address for the server
    private Integer GAME_PORT = 15636; // Optional, Game port for the server
    private Integer QUERY_PORT = 15637; // Optional, Query port for the server
    private Integer SLOT_COUNT = 16; // Optional, Number of slots for the server


}
