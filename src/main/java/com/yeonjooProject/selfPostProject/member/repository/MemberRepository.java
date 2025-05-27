package com.yeonjooProject.selfPostProject.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.yeonjooProject.selfPostProject.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    boolean existsByUsername(String username); // 사용자 이름 중복 체크
    boolean existsByEmail(String email); // 이메일 중복 체크
}
