package com.munggle.post.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("postTitle")
    private String postTitle;

    @Size(max = 500)
    @JsonProperty("postContent")
    private String postContent;

    // 포스트 이미지
    private List<MultipartFile> images;

    // 해시태그
    @Builder.Default
    @JsonProperty("hashtags")
    private List<String> hashtags = new ArrayList<>();

    @JsonProperty("isPrivate")
    private Boolean isPrivate;
}
