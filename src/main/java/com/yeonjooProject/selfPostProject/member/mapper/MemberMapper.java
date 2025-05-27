package com.yeonjooProject.selfPostProject.member.mapper;

//import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
//import com.yeonjooProject.selfPostProject.member.dto.MemberResponseDTO;
//import com.yeonjooProject.selfPostProject.member.entity.Member;
//import org.h2.engine.Role;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MemberMapper {
//
//    // DTO -> Entity 변환
//    public Member toEntity(MemberRequestDTO dto) {
//        return Member.builder()
//                .username(dto.getUsername())
//                .password(dto.getPassword())
//                .email(dto.getEmail())
//                .role(dto.getRole() == null ? "USER" : dto.getRole()) // 기본값 USER
//                .build();
//    }
//
//    // Entity -> DTO 변환
//    public MemberResponseDTO toDTO(Member member) {
//        return MemberResponseDTO.builder()
//                .id(member.getId())
//                .username(member.getUsername())
//                .email(member.getEmail())
//                .role(member.getRole())
//                .build();
//    }
//}

import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.MemberResponseDTO;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.entity.Member.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    // DTO -> Entity 변환
    public Member toEntity(MemberRequestDTO dto) {
        return Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .role(convertToRole(dto.getRole())) // 문자열 -> Role Enum 변환
                .build();
    }

    // Entity -> DTO 변환
    public MemberResponseDTO toDTO(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .role(member.getRole().name()) // Role Enum -> 문자열 변환
                .build();
    }

    // 문자열 -> Role Enum 변환
    private Role convertToRole(String role) {
        // 1. null 또는 빈 값일 경우 기본값 USER 설정
        if (role == null || role.trim().isEmpty()) {
            return Role.USER; // 기본값 설정
        }

        // 2. 문자열을 대문자로 변환 후 Role Enum으로 변환
        try {
            return Role.valueOf(role.toUpperCase()); // 대소문자 변환 후 Enum 반환
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("유효하지 않은 역할 값입니다: " + role); // 잘못된 값 처리
        }
    }
}
