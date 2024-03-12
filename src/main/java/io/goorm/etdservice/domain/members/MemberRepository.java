package io.goorm.etdservice.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findMemberByAttributeIdAndAuth(String attributeId, AuthType auth);
//    Optional<Member> findMemberByAttributeIdAndAuthFetchToken(String attributeId, AuthType auth);



}
