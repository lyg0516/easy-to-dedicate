package io.goorm.etdservice.domain.members;


import io.goorm.etdservice.global.security.OAuth2Attribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Member upsertMemberByOAuth(OAuth2Attribute attribute) {
        String attributeId = attribute.getAttributeId();
        AuthType auth = attribute.getAuth();


        Member member = memberRepository.findMemberByAttributeIdAndAuth(attributeId, auth)
                .orElseGet(() -> Member.toEntity(attribute));

        return memberRepository.save(member);

    }


}
