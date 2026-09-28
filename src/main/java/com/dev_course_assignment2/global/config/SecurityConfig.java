package com.dev_course_assignment2.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // NOTE: 웹 보안 설정
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT 사용 시 STATELESS 권장
                .authorizeHttpRequests(auth -> auth
                        // 읽기는 누구나 허용
                        .requestMatchers(HttpMethod.GET, "/api/posts/**", "/api/comments/**").permitAll()
                        // 회원가입, 로그인 허용
                        .requestMatchers(HttpMethod.POST, "/api/v1/members").permitAll()
                        // Swagger UI 및 OpenAPI 명세 조회 허용
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // 그 외 요청(쓰기, 수정, 삭제 등)은 인증 필요
                        .anyRequest().authenticated()
                );
        // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.ENABLED); // JWT 필터 추가 위치

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
