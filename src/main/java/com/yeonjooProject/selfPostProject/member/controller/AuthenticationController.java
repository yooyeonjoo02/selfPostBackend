package com.yeonjooProject.selfPostProject.member.controller;

import com.yeonjooProject.selfPostProject.config.JwtUtil;
import com.yeonjooProject.selfPostProject.member.dto.AuthenticationRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.AuthenticationResponseDTO;
import com.yeonjooProject.selfPostProject.member.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

//    @PostMapping("/login")
//    public AuthenticationResponseDTO login(@RequestBody @Valid AuthenticationRequestDTO request) {
//        // 사용자 인증
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//        System.out.println("Authentication successful: " + authentication);
//
//        // 인증된 사용자 정보
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        System.out.println("UserDetails: " + userDetails);
//
//        // JWT 생성
//        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority());
//        System.out.println("Generated Token: " + token);
//
//        // JWT 반환
//        return new AuthenticationResponseDTO(token);
//    }
    @PostMapping("/login")
    public AuthenticationResponseDTO login(@RequestBody @Valid AuthenticationRequestDTO request) {
        // 사용자 인증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()) // 이메일 사용
        );
        System.out.println("Authentication successful: " + authentication);

        // 인증된 사용자 정보
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("UserDetails: " + userDetails);

        // JWT 생성
        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority());
        System.out.println("Generated Token: " + token);

        // JWT 반환
        return new AuthenticationResponseDTO(token);
    }

}