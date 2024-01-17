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
public class PostUpdateDto {

    @Size(max = 100)
    @NotBlank
    private String postTitle;

    @Size(max = 500)
    private String postContent;

    private Boolean isPrivate;
}
