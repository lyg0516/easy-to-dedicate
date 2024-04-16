package io.goorm.etdservice.domain.servers.types;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ProgressType {

    RECEIVE("receive"),
    SUCCESS("success"),
    FAIL("failure");

    private String label;

    ProgressType(String label) {
        this.label = label;
    }

    private static final Map<String, ProgressType> BY_KEY =
            Stream.of(values())
                    .collect(Collectors.toMap(ProgressType::getLabel, Function.identity()));

    public static ProgressType valueOfLabel(String insertKey) {
        return BY_KEY.get(insertKey);
    }


}
