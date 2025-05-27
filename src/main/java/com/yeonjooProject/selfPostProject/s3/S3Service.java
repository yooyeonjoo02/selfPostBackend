package com.yeonjooProject.selfPostProject.s3;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;


//@Service
//@RequiredArgsConstructor
//public class S3Service {
//
//    private final S3Client s3Client;
//    private final String bucketName = "jeonjooselfproject"; // application.properties에서 설정한 버킷 이름
//    private final String region = "ap-northeast-2"; // application.properties에서 설정한 리전
//
//    public String uploadFile(MultipartFile file, String folderPath) {
//        String fileName = folderPath + file.getOriginalFilename();
//        try {
//            // S3에 파일 업로드
//            s3Client.putObject(
//                    PutObjectRequest.builder()
//                            .bucket(bucketName) // S3 버킷 이름
//                            .key(fileName) // 업로드할 파일 경로
////                            .acl("public-read") // 파일 공개 설정
//                            .build(),
//                    RequestBody.fromBytes(file.getBytes())
//            );
//
//            // 업로드된 파일의 URL 반환
//            return String.format("https://%s.s3.%s.amazonaws.com/%s",
//                    bucketName, region, fileName);
//        } catch (Exception e) {
//            throw new RuntimeException("S3 파일 업로드 실패", e);
//        }
//    }
//}

//@Service
//@RequiredArgsConstructor
//public class S3Service {
//
//    private final S3Client s3Client;
//    private final String bucketName = "jeonjooselfproject"; // application.properties에서 설정한 버킷 이름
//    private final String region = "ap-northeast-2"; // application.properties에서 설정한 리전
//
//    public String uploadFile(MultipartFile file, String folderPath) {
//        String fileName = folderPath + file.getOriginalFilename();
//        try {
//            // S3에 파일 업로드
//            s3Client.putObject(
//                    PutObjectRequest.builder()
//                            .bucket(bucketName) // S3 버킷 이름
//                            .key(fileName) // 업로드할 파일 경로
////                            .acl(ObjectCannedACL.PUBLIC_READ) // 파일 공개 설정
//                            .build(),
//                    RequestBody.fromBytes(file.getBytes())
//            );
//
//            // 업로드된 파일의 URL 반환
//            return String.format("https://%s.s3.%s.amazonaws.com/%s",
//                    bucketName, region, fileName);
//        } catch (Exception e) {
//            throw new RuntimeException("S3 파일 업로드 실패", e);
//        }
//    }
//}

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName = "jeonjooselfproject"; // application.properties에서 설정한 버킷 이름
    private final String region = "ap-northeast-2"; // application.properties에서 설정한 리전

    // ✅ S3 파일 업로드 메서드
    public String uploadFile(MultipartFile file, String folderPath) {
        String fileName = folderPath + file.getOriginalFilename();
        try {
            // S3에 파일 업로드
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName) // S3 버킷 이름
                            .key(fileName) // 업로드할 파일 경로
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            // 업로드된 파일의 URL 반환
            return String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName, region, fileName);
        } catch (Exception e) {
            throw new RuntimeException("S3 파일 업로드 실패", e);
        }
    }

    // ✅ S3 파일 삭제 메서드 추가
    public void deleteFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return; // 삭제할 이미지가 없으면 그냥 리턴
        }

        // S3에서 삭제하려면 key(파일 경로) 추출 필요
        String key = extractKeyFromUrl(imageUrl);

        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("S3 파일 삭제 실패: " + imageUrl, e);
        }
    }

    // ✅ S3 URL에서 key(파일 경로) 추출하는 메서드 추가
    private String extractKeyFromUrl(String imageUrl) {
        String prefix = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, region);
        return imageUrl.replace(prefix, "");
    }
}
