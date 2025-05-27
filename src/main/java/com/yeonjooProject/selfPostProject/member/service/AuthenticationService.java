package com.yeonjooProject.selfPostProject.member.service;

import com.yeonjooProject.selfPostProject.config.JwtUtil;
import com.yeonjooProject.selfPostProject.member.dto.AuthenticationRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.AuthenticationResponseDTO;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 로그인 요청을 처리하는 서비스
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final MemberRepository memberRepository;
//    private final JwtUtil jwtUtil;
//
//    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
//        // 사용자 검증
//        Member member = memberRepository.findByUsername(requestDTO.getUsername())
//                .orElseThrow(() -> new RuntimeException("사용자 이름을 찾을 수 없습니다."));
//
//        if (!member.getPassword().equals(requestDTO.getPassword())) {
//            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // JWT 토큰 생성
////        String token = jwtUtil.generateToken(member.getUsername());
//        String token = jwtUtil.generateToken(member.getUsername(), member.getRole().name());
//        return AuthenticationResponseDTO.builder().token(token).build();
//    }
//}

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
        // 사용자 검증
        Member member = memberRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일을 찾을 수 없습니다."));

        if (!member.getPassword().equals(requestDTO.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        String token = jwtUtil.generateToken(member.getEmail(), member.getRole().name());

        return AuthenticationResponseDTO.builder().token(token).build();
    }
}
