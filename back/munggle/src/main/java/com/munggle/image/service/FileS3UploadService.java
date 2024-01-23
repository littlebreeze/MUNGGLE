package com.munggle.image.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

public interface FileS3UploadService {
    String uploadFile(String uploadPath, MultipartFile multipartFile);

    void imageRemove(String fileName);
}
