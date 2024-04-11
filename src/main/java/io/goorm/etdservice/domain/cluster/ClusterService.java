package io.goorm.etdservice.domain.cluster;


import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClusterService {

    private final ClusterRepository clusterRepository;


    public UUID createCluster(ClusterDto clusterDto) {
        Cluster cluster = Cluster.builder()
                .applicationType(clusterDto.getApplicationType())
                .deployType(clusterDto.getDeployType())
                .name(clusterDto.getName())
                .location(clusterDto.getLocation())
                .description(clusterDto.getDescription())
                .externalIp(clusterDto.getExternalIp())
                .domain(clusterDto.getDomain())
                .build();

        Cluster savedCluster = clusterRepository.save(cluster);
        return savedCluster.getId();
    }


    @SneakyThrows
    public void updateCluster(UUID clusterId, ClusterDto clusterDto) {
        Cluster cluster = clusterRepository.findById(clusterId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "해당 클러스터를 찾을 수 없습니다."));
        cluster.updateCluster(clusterDto);
    }

    @SneakyThrows
    public ClusterDto getCluster(UUID clusterId) {
        Cluster cluster = clusterRepository.findById(clusterId)
                .orElseThrow(() -> new DomainException(ErrorCode.NOT_FOUND_DATA, "해당 클러스터를 찾을 수 없습니다."));

        return ClusterDto.toDto(cluster);
    }

    public List<ClusterDto> getClusters() {
        List<Cluster> clusters = clusterRepository.findAll();
        return clusters.stream().map(ClusterDto::toDto).collect(Collectors.toList());
    }

    public void deleteCluster(UUID clusterId) {
        clusterRepository.deleteById(clusterId);
    }
}
