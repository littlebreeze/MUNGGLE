package com.munggle.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.munggle.image.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileS3UploadService {
    FileInfoDto uploadFile(String uploadPath, MultipartFile multipartFile);

    List<FileInfoDto> uploadFlieList(String uploadPath, List<MultipartFile> multipartFiles);

    void removeFile(String fileName);
}
