package com.yeonjooProject.selfPostProject.member.service;

import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
//
//        // Spring Security의 User 객체를 반환
//        return new org.springframework.security.core.userdetails.User(
//                member.getUsername(),
//                member.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()))
//        );
//    }
//}

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + email));

        // Spring Security의 User 객체를 반환
        return new org.springframework.security.core.userdetails.User(
                member.getEmail(), // 이메일 사용
                member.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()))
        );
    }
}
