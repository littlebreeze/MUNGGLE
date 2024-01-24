package com.munggle.image.service;

//import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.munggle.image.dto.FileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class FileS3UploadServiceImpl implements FileS3UploadService {
    private final AmazonS3Client amazonS3Client;

    @Value("cloud.aws.s3.bucket")
    private String bucketName;

    /**
     * 파일이름 랜덤생성 메소드
     *
     * @param extension
     * @return
     */
    private String generateRandomName(String extension) {
        String random = UUID.randomUUID().toString();
        return random + extension;
    }

    /**
     * 확장자 제한하는 메소드
     *
     * @param extension
     * @return
     */
    private String getContentType(String extension) {
        switch (extension.toLowerCase()) {
            case "jpeg":
            case "jpg":
            case "png":
                return "image/" + extension;
            case "mp4":
                return "video/" + extension;
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + extension);
        }
    }

    /**
     * S3저장소에 파일 업로드
     * 
     * @param uploadPath : 저장 폴더 명
     * @param multipartFile
     * @return : 이미지가 저장된 주소 (URL)
     */
    @Override
    public FileInfoDto uploadFile(String uploadPath, MultipartFile multipartFile) {
        String originName = multipartFile.getOriginalFilename(); //원본 이미지 이름
        String ext = StringUtils.getFilenameExtension(originName); //확장자

        String generatedName = uploadPath + "/" + generateRandomName(ext); //새로 생성된 이미지 이름

        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
        metadata.setContentType(getContentType(ext)); //메타데이터 컨텐츠 타입 설정

        try { //S3 버킷에 파일 업로드
            amazonS3Client.putObject(new PutObjectRequest(
                    bucketName, generatedName, multipartFile.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileURL = amazonS3Client.getUrl(bucketName, generatedName).toString(); //데이터베이스에 저장할 이미지가 저장된 주소

        return FileInfoDto.builder()
                .fileName(generatedName)
                .fileURL(fileURL)
                .build();
    }

    /**
     * 파일을 여러개 저장하는 메소드
     *
     * @param uploadPath
     * @param multipartFiles
     * @return
     */
    @Override
    public List<FileInfoDto> uploadFlieList(String uploadPath, List<MultipartFile> multipartFiles) {

        List<FileInfoDto> fileInfoDtos = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            fileInfoDtos.add(uploadFile(uploadPath, file));
        }

        return fileInfoDtos;
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
