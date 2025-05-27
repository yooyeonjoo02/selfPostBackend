package com.yeonjooProject.selfPostProject.config;

import com.yeonjooProject.selfPostProject.member.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// JWT 인증 필터를 구현한 코드

//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//    private final CustomUserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//
//            try {
//                String username = jwtUtil.extractUsername(token);
//
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {
//                        String role = jwtUtil.extractRole(token); // JWT에서 역할 추출
//
//                        // 로그로 역할 출력
//                        System.out.println("Extracted Role: " + role);
//
//                        // 권한 설정
//                        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
//                        UsernamePasswordAuthenticationToken authentication =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                        // SecurityContext에 저장된 인증 정보 출력
//                        System.out.println("SecurityContext Authentication: " +
//                                SecurityContextHolder.getContext().getAuthentication());
//                    }
//                }
//            } catch (Exception e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 1. 요청 정보를 출력 (최초 요청 시)
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
                String username = jwtUtil.extractUsername(token);
                System.out.println("Extracted Username: " + username); // JWT에서 사용자 이름 추출 후 출력

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                        String role = jwtUtil.extractRole(token); // JWT에서 역할 추출

                        // 로그로 역할 출력
                        System.out.println("Extracted Role: " + role);

                        // 권한 설정
                        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        // SecurityContext에 저장된 인증 정보 출력
                        System.out.println("SecurityContext Authentication: " +
                                SecurityContextHolder.getContext().getAuthentication());
                    }
                }
            } catch (Exception e) {
                // 2. 에러 발생 시 로그 출력
                System.err.println("JWT Authentication Error: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
        }

        // 3. 요청 처리 후 다음 필터로 넘김
        filterChain.doFilter(request, response);

        // 4. 필터 체인 후 SecurityContext 상태 출력
        System.out.println("Final SecurityContext Authentication: " +
                SecurityContextHolder.getContext().getAuthentication());
    }
}
