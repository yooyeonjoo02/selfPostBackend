package com.yeonjooProject.selfPostProject.member.controller;

import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.MemberResponseDTO;
import com.yeonjooProject.selfPostProject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; // 서비스 계층 의존성 주입

    // 회원 가입
    @PostMapping("/register")
    public ResponseEntity<MemberResponseDTO> registerMember(@RequestBody @Valid MemberRequestDTO requestDTO) {
        MemberResponseDTO responseDTO = memberService.registerMember(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 특정 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id) {
        MemberResponseDTO responseDTO = memberService.getMemberById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // 모든 회원 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {
        List<MemberResponseDTO> responseDTOList = memberService.getAllMembers();
        return ResponseEntity.ok(responseDTOList);
    }
}
