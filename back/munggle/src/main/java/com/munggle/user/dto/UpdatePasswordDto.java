package com.munggle.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePasswordDto {

    @NotBlank
    @Size(min = 8, max = 15)
    private String newPassword;

    @NotBlank
    @Size(min = 8, max = 15)
    private String newPasswordConfirmation;
}