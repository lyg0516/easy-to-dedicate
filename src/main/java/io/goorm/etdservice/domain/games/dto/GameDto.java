package io.goorm.etdservice.domain.games.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.games.entity.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GameDto {

    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private Boolean isActive;

    @Builder
    public GameDto(Long id, String name, String description, String imgUrl, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.isActive = isActive;
    }

    public static GameDto toDto(Game entity) {
        return GameDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .imgUrl(entity.getImgUrl())
                .isActive(entity.getIsActive())
                .build();
    }

}
