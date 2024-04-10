package io.goorm.etdservice.domain.cluster;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.goorm.etdservice.domain.common.entity.BaseEntity;
import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.servers.entity.Server;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
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
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Member member;                  // 클러스터 등록 멤버

    @Column()
    private String name;
    @Enumerated(EnumType.STRING)
    private LocationType location;
    @Column()
    private String description;
    @Column()
    private String externalIp;              // 클러스터 외부 접속 IP
    @Column()
    private String domain;                  // 클러스터 외부 접속 도메인

    @OneToMany(mappedBy = "cluster", fetch = FetchType.LAZY)
    private List<Server> servers;

    @Builder
    public Cluster(UUID id, ApplicationType applicationType, DeployType deployType, Member member, String name, LocationType location, String description, String externalIp, String domain, List<Server> servers) {
        this.id = id;
        this.applicationType = applicationType;
        this.deployType = deployType;
        this.member = member;
        this.name = name;
        this.location = location;
        this.description = description;
        this.externalIp = externalIp;
        this.domain = domain;
        this.servers = servers;
    }

    public void updateCluster(ClusterDto dto) {
        this.applicationType = dto.getApplicationType();
        this.deployType = dto.getDeployType();
        this.description = dto.getDescription();
        this.externalIp = dto.getExternalIp();
        this.domain = dto.getDomain();
    }

}
