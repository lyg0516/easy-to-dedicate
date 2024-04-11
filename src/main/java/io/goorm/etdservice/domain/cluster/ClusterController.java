package io.goorm.etdservice.domain.cluster;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/clusters")
@RequiredArgsConstructor
@Slf4j
public class ClusterController {

    private final ClusterService clusterService;


    @PostMapping
    public ResponseEntity createCluster(@RequestBody ClusterDto clusterDto) {
        UUID clusterId = clusterService.createCluster(clusterDto);
        return ResponseEntity.status(201).body(clusterId);
    }

    @PutMapping("/{clusterId}")
    public ResponseEntity updateCluster(@PathVariable("clusterId") UUID clusterId,
                                        @RequestBody ClusterDto clusterDto) {
        clusterService.updateCluster(clusterId, clusterDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getClusters() {
        List<ClusterDto> clusters = clusterService.getClusters();
        return ResponseEntity.ok().body(clusters);
    }

    @GetMapping("/{clusterId}")
    public ResponseEntity getCluster(@PathVariable("clusterId") UUID clusterId) {
        ClusterDto cluster = clusterService.getCluster(clusterId);
        return ResponseEntity.ok().body(cluster);
    }

    @DeleteMapping("/{clusterId}")
    public ResponseEntity deleteCluster(@PathVariable("clusterId") UUID clusterId) {
        clusterService.deleteCluster(clusterId);
        return ResponseEntity.ok().build();
    }



}
