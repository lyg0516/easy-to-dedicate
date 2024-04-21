package io.goorm.etdservice.domain.servers.repository;

import io.goorm.etdservice.domain.servers.entity.Server;
import io.goorm.etdservice.domain.servers.entity.ServerControl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerControlRepository extends JpaRepository<ServerControl, Long> {
    List<ServerControl> findAllByServer(Server server);

}
