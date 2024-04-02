package io.goorm.etdservice.domain.games.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Game extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;
    @Column(length = 30)
    private String name;
    @Column
    private String description;

    @Column
    private String imgUrl;

    @Column
    private Boolean isActive = false;

    //TODO 지정 옵션 목록 데이터 컬럼 만들기
    //TODO Thumbnail Image 연결 - S3 사용??

    @Builder
    public Game(Long id, String name, String description, String imgUrl, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.isActive = isActive;
    }
}
