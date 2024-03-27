package io.goorm.etdservice.global.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        String token = jwtTokenUtil.generateRegisterToken(authentication);

        // 토큰을 쿼리 파라미터로 포함하여 리다이렉트 URL 설정
        String redirectUrl = "/?token=" + token;

        // 클라이언트로 리다이렉트
        response.sendRedirect(redirectUrl);

    }
}
