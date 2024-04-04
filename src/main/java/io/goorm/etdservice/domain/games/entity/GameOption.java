package io.goorm.etdservice.domain.games.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "gameOption")
@Getter
public class GameOption<T> {
    @Id
    private UUID serverID;

    private T gameOption;

    @Builder

    public GameOption(UUID serverID, T gameOption) {
        this.serverID = serverID;
        this.gameOption = gameOption;
    }
}
