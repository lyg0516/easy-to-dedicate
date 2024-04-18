package io.goorm.etdservice.domain.servers.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.cluster.Cluster;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.games.entity.Game;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.servers.types.TermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Member member;                  // 서버 소유 멤버
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Game game;                      // 서버의 게임 종류
    @Enumerated(EnumType.STRING)
    private TermType term;                  // 고정 기간제, 시간제
    @Column(length = 8)
    private Integer cpu;                    // 할당 리소스 CPU
    @Column(length = 64)
    private Integer ram;                    // 할당 리소스 RAM
    @Column()
    private Integer slot;                   // 슬롯 - Player 수
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cluster_id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Cluster cluster;                // 서버가 속해있는 클러스터 정보
    @Column()
    private String host;
    @Column()
    private Integer port;
    @Column(length = 365)
    private Integer days;                   // 서버 활성화 기간

    //TODO 서버와 요청 관련 테이블을 분리하는게 나은가? 고민할 것!
    //TODO 추가 파라메터들은 OneToOne 으로 분리 방향 고려할 것!

    @Builder
    public Server(UUID id, Member member, Game game, TermType term, Integer cpu, Integer ram, Integer slot, Cluster cluster, String host, Integer port, Integer days) {
        this.id = id;
        this.member = member;
        this.game = game;
        this.term = term;
        this.cpu = cpu;
        this.ram = ram;
        this.slot = slot;
        this.cluster = cluster;
        this.host = host;
        this.port = port;
        this.days = days;
    }

    public void setServerHost(String host, Integer port){
        this.host = host;
        this.port = port;
    }
}
