package com.yeonjooProject.selfPostProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

// CORS 설정을 위한 Spring Security 관련 설정 코드
// CORS => 다른 도메인(혹은 포트)에서 요청을 보내는 것을 허용하거나 제한하는 메커니즘
// React 개발 서버(localhost:3000)에서 API 서버(localhost:8080)로 요청을 보낼 때, 브라우저는 보안을 위해 이를 차단할 수 있습니다.
// CORS 설정을 통해 이런 요청을 허용할 수 있습니다.

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 개발 서버
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
