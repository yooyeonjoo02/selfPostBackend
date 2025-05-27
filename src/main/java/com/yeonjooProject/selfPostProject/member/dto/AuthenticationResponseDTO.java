package com.yeonjooProject.selfPostProject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 서버가 로그인 성공 후 클라이언트에게 반환하는 JWT 토큰을 담는 객체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponseDTO {
    private String token;
}
