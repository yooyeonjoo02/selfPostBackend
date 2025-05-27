package com.yeonjooProject.selfPostProject.member.service;

import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.MemberResponseDTO;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.mapper.MemberMapper;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//@Transactional
//public class MemberService {
//
//    private final MemberRepository memberRepository; // Repository 의존성 주입
//    private final MemberMapper memberMapper; // 매퍼 의존성 주입
//
//    // 회원 가입
//    public MemberResponseDTO registerMember(MemberRequestDTO requestDTO) {
//        // 1. DTO -> Entity 변환
//        Member member = memberMapper.toEntity(requestDTO);
//
//        // 2. 중복 체크 (username, email)
//        validateDuplicateMember(requestDTO);
//
//        // 3. 저장
//        Member savedMember = memberRepository.save(member);
//
//        // 4. Entity -> DTO 변환 후 반환
//        return memberMapper.toDTO(savedMember);
//    }
//
//    // 특정 회원 정보 조회
//    public MemberResponseDTO getMemberById(Long id) {
//        // 1. ID로 회원 조회 (예외 처리 포함)
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));
//
//        // 2. Entity -> DTO 변환 후 반환
//        return memberMapper.toDTO(member);
//    }
//
//    // 모든 회원 조회
//    public List<MemberResponseDTO> getAllMembers() {
//        // 1. 모든 회원 조회
//        List<Member> members = memberRepository.findAll();
//
//        // 2. Entity -> DTO 변환 후 반환
//        return members.stream()
//                .map(memberMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    // 중복 체크
//    private void validateDuplicateMember(MemberRequestDTO requestDTO) {
//        if (memberRepository.existsByUsername(requestDTO.getUsername())) {
//            throw new RuntimeException("이미 존재하는 사용자 이름입니다.");
//        }
//        if (memberRepository.existsByEmail(requestDTO.getEmail())) {
//            throw new RuntimeException("이미 존재하는 이메일입니다.");
//        }
//    }
//}

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository; // Repository 의존성 주입
    private final MemberMapper memberMapper; // 매퍼 의존성 주입
    private final PasswordEncoder passwordEncoder; // BCryptPasswordEncoder 의존성 주입

    // 회원 가입
    public MemberResponseDTO registerMember(MemberRequestDTO requestDTO) {
        // 1. DTO -> Entity 변환
        Member member = memberMapper.toEntity(requestDTO);

        // 2. 비밀번호 암호화 후 설정
        String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());
        member.encodePassword(encodedPassword);

        // 3. 중복 체크
        validateDuplicateMember(requestDTO);

        // 4. 저장
        Member savedMember = memberRepository.save(member);

        // 5. Entity -> DTO 변환 후 반환
        return memberMapper.toDTO(savedMember);
    }


    // 특정 회원 정보 조회
    public MemberResponseDTO getMemberById(Long id) {
        // 1. ID로 회원 조회 (예외 처리 포함)
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        // 2. Entity -> DTO 변환 후 반환
        return memberMapper.toDTO(member);
    }

    // 모든 회원 조회
    public List<MemberResponseDTO> getAllMembers() {
        // 1. 모든 회원 조회
        List<Member> members = memberRepository.findAll();

        // 2. Entity -> DTO 변환 후 반환
        return members.stream()
                .map(memberMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 중복 체크
    private void validateDuplicateMember(MemberRequestDTO requestDTO) {
        if (memberRepository.existsByUsername(requestDTO.getUsername())) {
            throw new RuntimeException("이미 존재하는 사용자 이름입니다.");
        }
        if (memberRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
    }
}
