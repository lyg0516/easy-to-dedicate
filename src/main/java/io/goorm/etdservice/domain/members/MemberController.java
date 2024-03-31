package io.goorm.etdservice.domain.members;


import io.goorm.etdservice.domain.servers.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("${api-prefix}/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ServerService serverService;

    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable() UUID memberId) {
        MemberDto member = memberService.getMember(memberId);
        return ResponseEntity.ok().body(member);
    }
}
