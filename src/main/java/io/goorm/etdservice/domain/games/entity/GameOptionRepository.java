package io.goorm.etdservice.domain.games.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameOptionRepository extends MongoRepository<GameOption, UUID> {

}
