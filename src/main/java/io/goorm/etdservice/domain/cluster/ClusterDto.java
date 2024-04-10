package io.goorm.etdservice.domain.cluster;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClusterDto {
    private UUID id;
    private ApplicationType applicationType;
    private DeployType deployType;
    private String description;
    private String externalIp;
    private String domain;

    @Builder
    public ClusterDto(UUID id, ApplicationType applicationType, DeployType deployType, String description, String externalIp, String domain) {
        this.id = id;
        this.applicationType = applicationType;
        this.deployType = deployType;
        this.description = description;
        this.externalIp = externalIp;
        this.domain = domain;
    }

    public static ClusterDto toDto(Cluster entity) {
        return ClusterDto.builder()
                .id(entity.getId())
                .applicationType(entity.getApplicationType())
                .deployType(entity.getDeployType())
                .description(entity.getDescription())
                .externalIp(entity.getExternalIp())
                .domain(entity.getDomain())
                .build();
    }


}