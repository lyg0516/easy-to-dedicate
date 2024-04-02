package io.goorm.etdservice.domain.games.types;

import lombok.Getter;

@Getter
public enum PlatformType {

    STEAM("STEAM",null),
    PLAYSTATION("PLAYSTATION",null),
    XBOX("XBOX",null);

    private String code;
    private String imgUrl;

    PlatformType(String code, String imgUrl) {
        this.code = code;
        this.imgUrl = imgUrl;
    }
}
