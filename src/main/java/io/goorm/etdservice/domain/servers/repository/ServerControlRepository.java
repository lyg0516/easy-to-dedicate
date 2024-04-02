package io.goorm.etdservice.domain.servers.repository;

import io.goorm.etdservice.domain.servers.entity.ServerControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerControlRepository extends JpaRepository<ServerControl, Long> {

}
