package com.munggle.image.service;


import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import com.munggle.image.dto.FileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final S3Client s3Client;

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
     *                   : 게시글은 post/postId/ 이런식으로 만들어서 관리
     * @param multipartFile
     * @return : 이미지가 저장된 주소 (URL)
     */
    @Override
    public FileInfoDto uploadFile(String uploadPath, MultipartFile multipartFile) {
        String originName = multipartFile.getOriginalFilename(); //원본 이미지 이름
        String ext = StringUtils.getFilenameExtension(originName); //확장자

        String generatedName = uploadPath + "/" + generateRandomName(ext); //새로 생성된 이미지 이름

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(generatedName)
                .contentType(getContentType(ext))
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileURL = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(generatedName).build()).toExternalForm();
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
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        s3Client.deleteObject(deleteRequest);
    }

}
