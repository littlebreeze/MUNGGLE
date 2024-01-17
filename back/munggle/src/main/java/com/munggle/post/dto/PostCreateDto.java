package com.munggle.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
@AllArgsConstructor
public class PostCreateDto {

    @JsonIgnore
    private Long userId;

    @Size(max = 100)
    @NotBlank
    private String postTitle;

    @Size(max = 500)
    private String postContent;

//    포스트 이미지
//    @NotEmpty
//    @Builder.Default
//    private List<MultipartFile> images = new ArrayList<>();

//    해시태그
//    @Builder.Default
//    private List<String> hashtags = new ArrayList<>();

    private Boolean isPrivate;
}
