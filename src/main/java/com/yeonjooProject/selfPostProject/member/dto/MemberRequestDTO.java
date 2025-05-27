package com.yeonjooProject.selfPostProject.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 회원 가입 시 필요한 데이터를 클라이언트로부터 받는 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {

    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private String role; // 기본값으로 USER를 설정하거나 별도 처리 가능
}
