package com.yeonjooProject.selfPostProject.member.dto;

import lombok.*;

// 회원 정보를 클라이언트로 전달할 때 사용할 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDTO {

    private Long id;

    private String username;

    private String email;

    private String role;
}
