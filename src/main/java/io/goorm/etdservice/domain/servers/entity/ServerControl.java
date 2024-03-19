package io.goorm.etdservice.domain.servers.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.servers.types.ControlType;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private Server server;
    @Enumerated(EnumType.STRING)
    private ControlType control;
    private LocalDateTime appliedAt;

}