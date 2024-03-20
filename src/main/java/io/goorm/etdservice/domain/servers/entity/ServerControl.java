package io.goorm.etdservice.domain.servers.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.servers.types.ControlType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServerControl extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Server server;              // 요청된 서버
    @Enumerated(EnumType.STRING)
    private ControlType control;        // 컨트롤 타입, CREATE, RESTART, STOP
    @Column
    private LocalDateTime appliedAt;    // 적용된 시간

    @Builder
    public ServerControl(Long id, Server server, ControlType control, LocalDateTime appliedAt) {
        this.id = id;
        this.server = server;
        this.control = control;
        this.appliedAt = appliedAt;
    }
}
