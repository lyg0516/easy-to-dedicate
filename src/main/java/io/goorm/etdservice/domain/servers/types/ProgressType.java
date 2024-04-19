package io.goorm.etdservice.domain.servers.types;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ProgressType {

    RECEIVE("receive"),     // 처음 클라이언트로부터 요청을 받았을때 값, Default Value
    SUCCESS("success"),     // 게임 디플로이로부터 받은 메세지, 성공
    FAIL("failure");        // 게임 디플로이로부터 받은 메세지, 실패

    private String label;

    ProgressType(String label) {
        this.label = label;
    }

    private static final Map<String, ProgressType> BY_KEY =
            Stream.of(values())
                    .collect(Collectors.toMap(ProgressType::getLabel, Function.identity()));


    // MQ로 받은 string 값을 넣어 찾을때 쓴다. 게임 디플로이에서 보내는 값을 기준으로 label 을 넣음
    public static ProgressType valueOfLabel(String insertKey) {
        return BY_KEY.get(insertKey);
    }


}
