package com.yeonjooProject.selfPostProject.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

// 클라이언트가 로그인할 때 보내는 **username**과 **password**를 담는 객체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequestDTO {
    @NotBlank(message = "사용자 이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}