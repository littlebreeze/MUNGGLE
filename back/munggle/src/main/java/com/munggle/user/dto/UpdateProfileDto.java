package com.munggle.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateProfileDto {

    @NotBlank
    @Size(min = 2, max = 10)
    private String newNickname;

    @Size(max = 100)
    private String description;
}
