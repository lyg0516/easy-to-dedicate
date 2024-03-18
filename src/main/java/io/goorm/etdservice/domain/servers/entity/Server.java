package io.goorm.etdservice.domain.servers.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.games.entity.Game;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.common.types.TermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Server extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "server_id", length = 32, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
//    @Enumerated(EnumType.STRING)
//    private GameType game;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @Enumerated(EnumType.STRING)
    private TermType term;
    @Column(length = 8)
    private Integer cpu;
    @Column(length = 64)
    private Integer ram;
    @Column()
    private Integer slot;
    @Column(length = 100)
    private String location;
    @Column(length = 365)
    private Integer days;
    @Column()
    private LocalDateTime requestedAt;
    @Column()
    private LocalDateTime confirmedAt;

}
