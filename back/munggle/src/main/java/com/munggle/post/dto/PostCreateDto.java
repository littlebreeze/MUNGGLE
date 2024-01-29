package com.munggle.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {

    private Long userId;

    @Size(max = 100)
    @NotBlank
    private String postTitle;

    @Size(max = 500)
    private String postContent;

    // 포스트 이미지
    @Builder.Default
    private List<MultipartFile> images = new ArrayList<>();

    // 해시태그
    @Builder.Default
    private List<String> hashtags = new ArrayList<>();

    private Boolean isPrivate;
}
