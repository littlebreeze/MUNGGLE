package com.munggle.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePwdDto {

    /// 현재 비밀번호 확인은 나중에 구현
//    @NotBlank
//    @Size(min = 8, max = 15)
//    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 15)
    private String newPassword;
}