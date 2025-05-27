package com.yeonjooProject.selfPostProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 기존 코드
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",  // Swagger UI 허용
//                                "/v3/api-docs/**", // OpenAPI 문서 허용
//                                "/h2-console/**",   // H2 Console 허용
//                                "/api/members/**", // 멤버 API 허용
//                                "/api/categories/**", // 카테고리 API 허용
//                                "/api/posts/**", // 게시물 API 허용
//                                "/api/comments/**", // Comment API 허용
//                                "/api/banners/**", // 배너 API 허용
//                                "/api/info/**" // 배너 API 허용
//                        ).permitAll() // 위의 경로들은 모두 허용
//                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
//                )
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                "/h2-console/**", // H2 Console CSRF 비활성화
//                                "/swagger-ui/**", // Swagger UI CSRF 비활성화
//                                "/v3/api-docs/**", // OpenAPI CSRF 비활성화
//                                "/api/members/**", // 멤버 API 허용
//                                "/api/categories/**", // CSRF 비활성화
//                                "/api/posts/**", // 게시물 API 허용
//                                "/api/comments/**", // CSRF 비활성화
//                                "/api/banners/**", // 배너 API 허용
//                                "/api/info/**" // 배너 API 허용
//                        )
//                )
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin() // H2 Console에서 iframe 허용
//                );
//        return http.build();
//    }
//}


//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                "/h2-console/**",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        )
//                )
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin()
//                )
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/h2-console/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        ).permitAll()
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증을 사용하므로 세션 미사용
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 비밀번호 암호화에 사용
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                "/h2-console/**",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        )
//                )
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin()
//                )
//                .authorizeHttpRequests(auth -> auth
//                        // Swagger, H2 Console, 회원가입 및 로그인 허용
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/h2-console/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        ).permitAll()
//
//                        // 카테고리 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ROLE_ADMIN") // CREATE: ADMIN만
//                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN") // UPDATE: ADMIN만
//                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN") // DELETE: ADMIN만
//
//                        // 게시물 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/posts/**").hasRole("ADMIN") // CREATE: ADMIN만
//                        .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasRole("ADMIN") // UPDATE: ADMIN만
//                        .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasRole("ADMIN") // DELETE: ADMIN만
//
//                        // 댓글 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/comments/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/comments/**").hasRole("ADMIN") // CREATE: ADMIN만
//                        .requestMatchers(HttpMethod.PUT, "/api/comments/**").hasRole("ADMIN") // UPDATE: ADMIN만
//                        .requestMatchers(HttpMethod.DELETE, "/api/comments/**").hasRole("ADMIN") // DELETE: ADMIN만
//
//                        // 배너 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/banners/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/banners/**").hasRole("ADMIN") // CREATE: ADMIN만
//                        .requestMatchers(HttpMethod.PUT, "/api/banners/**").hasRole("ADMIN") // UPDATE: ADMIN만
//                        .requestMatchers(HttpMethod.DELETE, "/api/banners/**").hasRole("ADMIN") // DELETE: ADMIN만
//
//                        // 정보 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/info/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/info/**").hasRole("ADMIN") // CREATE: ADMIN만
//                        .requestMatchers(HttpMethod.PUT, "/api/info/**").hasRole("ADMIN") // UPDATE: ADMIN만
//                        .requestMatchers(HttpMethod.DELETE, "/api/info/**").hasRole("ADMIN") // DELETE: ADMIN만
//
//                        // 기타 모든 요청 인증 필요
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증 사용
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 비밀번호 암호화
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                "/h2-console/**",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        )
//                )
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin()
//                )
//                .authorizeHttpRequests(auth -> auth
//                        // Swagger, H2 Console, 회원가입 및 로그인 허용
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/h2-console/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        ).permitAll()
//
//                        // 카테고리 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_ADMIN만 허용
//                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_ADMIN만 허용
//                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_ADMIN만 허용
//
//                        // 기타 모든 요청 인증 필요
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증 사용
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 비밀번호 암호화
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

// 아래는 테스트 코드임 -> 테스트 환경에서 CSRF 비활성화
//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and() // WebConfig의 CORS 설정과 연계
//                .csrf(csrf -> csrf.disable()) // 테스트 환경에서 CSRF 비활성화
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin()
//                )
//                .authorizeHttpRequests(auth -> auth
//                        // Swagger, H2 Console, 회원가입 및 로그인 허용
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/h2-console/**",
//                                "/api/members/register",
//                                "/api/auth/login"
//                        ).permitAll()
//
//                        // 카테고리 CRUD 권한 설정
//                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() // READ: 모두 허용
//                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_ADMIN만 허용
//                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_ADMIN만 허용
//                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_ADMIN만 허용
//
//                        // 기타 모든 요청 인증 필요
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증 사용
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 비밀번호 암호화
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource; // CorsConfig와 연계

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CorsConfigurationSource corsConfigurationSource) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource)) // CorsConfig 연결
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers
                        .frameOptions().sameOrigin()
                )
                .authorizeHttpRequests(auth -> auth
                        // Swagger, H2 Console, 회원가입 및 로그인 허용
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**",
                                "/api/members/register",
                                "/api/auth/login"
                        ).permitAll()

                        // VideoPost API 권한 설정
                        .requestMatchers(HttpMethod.GET, "/api/videoposts/**").permitAll() // READ: 모두 허용
                        .requestMatchers(HttpMethod.POST, "/api/videoposts/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_USER만 허용
                        .requestMatchers(HttpMethod.PUT, "/api/videoposts/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_USER만 허용
                        .requestMatchers(HttpMethod.DELETE, "/api/videoposts/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_USER만 허용

                        // 카테고리 CRUD 권한 설정
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() // READ: 모두 허용
                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_ADMIN만 허용

                        // 배너 CRUD 권한 설정
                        .requestMatchers(HttpMethod.GET, "/api/banners/**").permitAll() // READ: 모두 허용
                        .requestMatchers(HttpMethod.POST, "/api/banners/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.PUT, "/api/banners/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.DELETE, "/api/banners/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_ADMIN만 허용

                        // 게시판 CRUD 권한 설정
                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll() // READ: 모두 허용
                        .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAuthority("ROLE_ADMIN") // CREATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAuthority("ROLE_ADMIN") // UPDATE: ROLE_ADMIN만 허용
                        .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAuthority("ROLE_ADMIN") // DELETE: ROLE_ADMIN만 허용

                        // 기타 모든 요청 인증 필요
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증 사용
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
