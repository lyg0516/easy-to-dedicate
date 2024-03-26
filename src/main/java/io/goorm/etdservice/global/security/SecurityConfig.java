package io.goorm.etdservice.global.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(    // Spring AOP 활용, 검증 어노테이션 사용 여부
        prePostEnabled = true,  // @PreAuthorize
        securedEnabled = true,  // @Secured
        jsr250Enabled = true)   // @RolesAllowed
public class SecurityConfig {

    private final OAuth2UserServiceImpl oAuth2UserService;
    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());


        http
                .authorizeHttpRequests(authorize -> authorize       // 권한 지정
                        // Static
                        .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()

                        // login
                        .requestMatchers(new AntPathRequestMatcher("/api/login/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()

                        // API
                        .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()

                        // Temp All Req
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                )
                // 세션 사용 설정
                .sessionManagement(session -> session
                        // 세션을 사용하지 않겠다는 의미
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/login")
                                .userInfoEndpoint(userInfo -> userInfo
                                        .userService(oAuth2UserService)
                                )
                                .successHandler(authenticationSuccessHandler)
//                        .failureHandler(oAuth2AuthenticationFailureHandler)
                );


        return http.build();
    }


}
