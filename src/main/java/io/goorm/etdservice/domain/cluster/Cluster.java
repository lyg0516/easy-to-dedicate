package io.goorm.etdservice.domain.cluster;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.members.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Cluster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cluster_id", length = 36)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column()
    private ApplicationType applicationType
            = ApplicationType.KUBERNETES;    // 쿠버네티스, 도커

    @Enumerated(EnumType.STRING)
    @Column()
    private DeployType deployType
            = DeployType.PUBLIC;            // 공개 또는 개인

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;                  // 클러스터 등록 멤버

    @Column()
    private String desc;

    @Column()
    private String externalIp;              // 클러스터 외부 접속 IP
    @Column()
    private String domain;                  // 클러스터 외부 접속 도메인

}
