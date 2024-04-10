package io.goorm.etdservice.domain.cluster;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/clusters")
@RequiredArgsConstructor
@Slf4j
public class ClusterController {

    private final ClusterService clusterService;


    @PostMapping
    public ResponseEntity createCluster(@RequestBody ClusterDto clusterDto) {
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity updateCluster(@RequestBody ClusterDto clusterDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getClusters() {
        clusterService.getClusters();
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{clusterId}")
    public ResponseEntity getCluster(@PathVariable("clusterId") UUID clusterId) {
        clusterService.getCluster(clusterId);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{clusterId}")
    public ResponseEntity deleteCluster(@PathVariable("clusterId") UUID clusterId) {
        clusterService.deleteCluster(clusterId);
        return ResponseEntity.ok().build();
    }



}
