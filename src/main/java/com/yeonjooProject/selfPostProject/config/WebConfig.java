package com.yeonjooProject.selfPostProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**") // API 경로
//                        .allowedOrigins("http://localhost:3000") // React 개발 서버 도메인
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
//                        .allowedHeaders("*") // 모든 헤더 허용
//                        .allowCredentials(true); // 인증 정보 허용
//            }
//        };
//    }
}
