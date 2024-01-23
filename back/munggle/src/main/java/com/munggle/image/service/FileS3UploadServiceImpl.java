package com.munggle.image.service;

//import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class FileS3UploadServiceImpl implements FileS3UploadService {
    private final AmazonS3Client amazonS3Client;

    @Value("cloud.aws.s3.bucket")
    private String bucketName;

    // 이미지 저장시 중복 방지를 위해 랜덤 이름 생성
    private String generateRandomName(String extension) {
        String random = UUID.randomUUID().toString();
        return random + extension;
    }

    /**
     * S3저장소에 파일 업로드
     * 
     * @param uploadPath : 저장 폴더 명
     * @param multipartFile
     * @return : 이미지가 저장된 주소 (URL)
     */
    @Override
    public String uploadFile(String uploadPath, MultipartFile multipartFile) {
        String originName = multipartFile.getOriginalFilename(); //원본 이미지 이름
        String ext = originName.substring(originName.lastIndexOf(".")); //확장자
        String generatedName = uploadPath + "/" + generateRandomName(ext); //새로 생성된 이미지 이름

        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
        metadata.setContentType("image/" + ext.substring(1)); //메타데이터 컨텐츠 타입 설정
        // 확장자 제한 시 해당 코드 구현 필요

        try {
            amazonS3Client.putObject(new PutObjectRequest(
                    bucketName, generatedName, multipartFile.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return amazonS3Client.getUrl(bucketName, generatedName).toString(); //데이터베이스에 저장할 이미지가 저장된 주소
    }


    /**
     * S3저장소에서 파일 삭제
     * 
     * @param fileName : db에 저장할 imageName에 해당
     */
    @Override
    public void removeFile(String fileName) {
        DeleteObjectRequest deleteRequest = new DeleteObjectRequest(bucketName, fileName);
        amazonS3Client.deleteObject(deleteRequest);
    }
}
