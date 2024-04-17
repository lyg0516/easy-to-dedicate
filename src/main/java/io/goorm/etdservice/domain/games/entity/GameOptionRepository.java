package io.goorm.etdservice.domain.games.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameOptionRepository extends MongoRepository<GameOption, UUID> {

}
