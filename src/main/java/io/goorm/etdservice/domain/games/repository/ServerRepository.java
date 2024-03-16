package io.goorm.etdservice.domain.games.repository;

import io.goorm.etdservice.domain.games.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServerRepository extends JpaRepository<Server, UUID> {

}
