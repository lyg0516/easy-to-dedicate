package io.goorm.etdservice.domain.servers.repository;

import io.goorm.etdservice.domain.servers.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServerRepository extends JpaRepository<Server, UUID> {

}
