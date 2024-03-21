package io.goorm.etdservice.global.s3.test;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String itemName;
    private String attachFile;
}
