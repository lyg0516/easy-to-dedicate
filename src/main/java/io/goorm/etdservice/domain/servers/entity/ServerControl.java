package io.goorm.etdservice.domain.servers.entity;

import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.servers.types.ControlType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class ServerControl extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Server server;
    private ControlType control;
    private LocalDateTime appliedAt;

}
