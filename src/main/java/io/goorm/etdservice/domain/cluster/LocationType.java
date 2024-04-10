package io.goorm.etdservice.domain.cluster;

import lombok.Getter;

@Getter
public enum LocationType {
    KR("KR","대한민국","/path"),
    EU("EU","유럽","/path"),
    USA("USA","미국","/path");

    private String code;
    private String name;
    private String img;

    LocationType(String code, String name, String img) {
        this.code = code;
        this.name = name;
        this.img = img;
    }
}
