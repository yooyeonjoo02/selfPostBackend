package com.yeonjooProject.selfPostProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;

//@Configuration
//public class S3Config {
//
//    @Bean
//    public S3Client s3Client() {
//        return S3Client.builder()
//                .region(Region.of("ap-northeast-2")) // 서울 리전 설정
//                .credentialsProvider(ProfileCredentialsProvider.create()) // AWS 자격 증명 자동 로드
//                .build();
//    }
//}

// Amazon S3 클라이언트를 설정하는 Spring Bean을 생성하는 설정 클래스
@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("ap-northeast-2")) // 서울 리전 설정
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()) // 환경 변수에서 자격 증명 로드
                .build();
    }
}
