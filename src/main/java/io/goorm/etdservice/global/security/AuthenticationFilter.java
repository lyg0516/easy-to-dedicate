package io.goorm.etdservice.global.security;

import io.goorm.etdservice.domain.members.Member;
import io.goorm.etdservice.domain.members.MemberRepository;
import io.goorm.etdservice.domain.members.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    // Token Util
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더에서 JWT 토큰을 추출합니다.
        String token = getJwtFromRequest(request);
        log.info("토큰 인증 필터 | token : {}",token);

        // 토큰이 존재하며 유효한 경우에만 작업을 수행합니다.
        if (token != null && jwtTokenUtil.validateToken(token)) {
            // 토큰에서 사용자명을 추출합니다.
            String username = jwtTokenUtil.getUserIdFromToken(token);

            // 사용자명을 사용하여 UserDetails 객체를 로드합니다.
            UserDetails userDetails = memberService.loadUserByUsername(username);

            // UserDetails를 사용하여 UsernamePasswordAuthenticationToken을 생성합니다.
            // 이 토큰은 인증된 사용자를 나타냅니다. 여기서는 패스워드를 null로 설정하며,
            // 이미 토큰을 통해 사용자의 신원이 검증되었기 때문에 안전합니다.
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


            // 요청에 대한 세부 사항을 설정합니다.
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // SecurityContext에 인증 객체를 설정하여, Spring Security에서 이 요청이 인증된 것으로 처리하도록 합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 필터 체인을 계속 진행합니다.
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // Request Header 에 Token 또는 API Key 를 추출
//        String authorizationHeader = httpRequest.getHeader("Authorization");
//
//        UsernamePasswordAuthenticationToken authentication;
//
//        // API Key 또는 Bearer Token 둘 중 하나로 인증 하기 위한 작업
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String bearerToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분 추출
//
//            if (jwtTokenUtil.validateToken(bearerToken)) {
//                // 유효기간에 대한 예외처리
//                // ...
//            }
//
//            // 토큰에서 Member 조회를 위한 값 추출
//            String attributeId = jwtTokenUtil.getUserIdFromToken(bearerToken);
//            String registrationId = jwtTokenUtil.getRegistrationIdFromToken(bearerToken);
//            // 등록된 Member 조회
//            Member member = memberRepository.findByAttributeIdAndRegistrationId(attributeId, registrationId)
//                    .orElseThrow(() -> new RuntimeException("Bearer Token 인증 실패!!"));
//
//            // 유저 인가 정보 설정 -> API 별 권한 체크에 사용됨
//            authentication = new UsernamePasswordAuthenticationToken(bearerToken, null,
//                    // 조회된 Member 에서 권한(Role) 입력
//                    Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())));
//
//        } else if (apiKeyHeader != null) {
//            String apiKey = apiKeyHeader;
//            // API Key를 이용하여 처리 (예: 인증 처리)
//            // ...
//            authentication = new UsernamePasswordAuthenticationToken(apiKey, null,
//                    Collections.singleton(new SimpleGrantedAuthority(Role.API.getKey())));
//
//        } else {
//            // 예외처리
//            // Guest 권한으로 처리(혹은 권한 인증 실패에 대한 예외처리를 할 것)
//            authentication = new UsernamePasswordAuthenticationToken(UUID.randomUUID(), null,
//                    Collections.singleton(new SimpleGrantedAuthority(Role.GUEST.getKey())));
//        }
//
//
//        // authentication 세팅
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        chain.doFilter(request, response);
//    }
}
