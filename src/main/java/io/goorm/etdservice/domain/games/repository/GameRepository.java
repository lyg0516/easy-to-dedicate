package io.goorm.etdservice.domain.games.repository;


import io.goorm.etdservice.domain.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
