package io.goorm.etdservice.domain.cluster;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClusterDto {
    private UUID id;
    private ApplicationType applicationType;
    private DeployType deployType;
    private String name;
    private LocationType location;
    private String description;
    private String externalIp;
    private String domain;

    private LocalDateTime createdAt;

    @Builder
    public ClusterDto(UUID id, ApplicationType applicationType, DeployType deployType, String name, LocationType location, String description, String externalIp, String domain, LocalDateTime createdAt) {
        this.id = id;
        this.applicationType = applicationType;
        this.deployType = deployType;
        this.name = name;
        this.location = location;
        this.description = description;
        this.externalIp = externalIp;
        this.domain = domain;
        this.createdAt = createdAt;
    }


    public static ClusterDto toDto(Cluster entity) {
        return ClusterDto.builder()
                .id(entity.getId())
                .applicationType(entity.getApplicationType())
                .deployType(entity.getDeployType())
                .name(entity.getName())
                .location(entity.getLocation())
                .description(entity.getDescription())
                .externalIp(entity.getExternalIp())
                .domain(entity.getDomain())
                .createdAt(entity.getCreatedAt())
                .build();
    }


}
