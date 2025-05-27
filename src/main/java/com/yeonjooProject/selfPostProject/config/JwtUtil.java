package com.yeonjooProject.selfPostProject.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

// JWT 관련 작업(토큰 생성, 검증, 파싱 등)을 담당하는 유틸리티 클래스
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    // JWT 토큰 생성
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("role", role) // 역할 추가
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public String extractClaim(String token, String claimKey) {
//        return Jwts.parser()
//                .setSigningKey(secret) // JWT 서명 키 사용
//                .parseClaimsJws(token) // 토큰 파싱
//                .getBody() // 클레임 본문 추출
//                .get(claimKey, String.class); // 지정된 클레임 키의 값을 반환
//    }
//
//    // 토큰에서 사용자 이름 추출
//    public String extractUsername(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    // 토큰 유효성 검사
//    public boolean validateToken(String token, String username) {
//        return username.equals(extractUsername(token)) && !isTokenExpired(token);
//    }
//
//    // 토큰 만료 여부 확인
//    private boolean isTokenExpired(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
//    }
//}

//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    // JWT 토큰 생성
//    public String generateToken(String username, String role) {
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("role", role) // 역할 추가
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    // 클레임 본문 추출 (공통 로직)
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // 특정 클레임 추출
//    public String extractClaim(String token, String claimKey) {
//        return extractAllClaims(token).get(claimKey, String.class);
//    }
//
//    // 토큰에서 사용자 이름 추출
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // 토큰 유효성 검사
//    public boolean validateToken(String token, String username) {
//        return username.equals(extractUsername(token)) && !isTokenExpired(token);
//    }
//
//    // 토큰 만료 여부 확인
//    private boolean isTokenExpired(String token) {
//        return extractAllClaims(token).getExpiration().before(new Date());
//    }
//}

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // JWT 토큰 생성
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // 사용자 이메일
                .claim("role", role) // 역할 추가
                .setIssuedAt(new Date()) // 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
                .signWith(SignatureAlgorithm.HS512, secret) // 서명 알고리즘 및 비밀 키
                .compact(); // 최종 JWT 문자열 반환
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token); // 토큰에서 username 추출
            return extractedUsername.equals(username) && !isTokenExpired(token); // 만료 여부와 username 검증
        } catch (Exception e) {
            return false; // 예외 발생 시 유효하지 않은 토큰으로 처리
        }
    }

    // 토큰에서 username 추출
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject(); // subject는 username으로 설정됨
    }

    // 토큰에서 역할(role) 추출
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class); // role 클레임 추출
    }

    // JWT 만료 여부 확인
    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration(); // 만료 시간 가져오기
        return expiration.before(new Date()); // 현재 시간과 비교
    }

    // JWT 클레임 전체 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret) // 비밀 키 설정
                .parseClaimsJws(token) // 토큰 파싱
                .getBody(); // 클레임 반환
    }
}