package io.goorm.etdservice.domain.servers.repository;

import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.servers.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServerRepository extends JpaRepository<Server, UUID> {
    List<Server> findByMember(Member member);

    @Query("select s from Server s join fetch s.game g where s.member.id = :memberId")
    List<Server> findByMemberId(@Param("memberId") UUID memberId);
}
