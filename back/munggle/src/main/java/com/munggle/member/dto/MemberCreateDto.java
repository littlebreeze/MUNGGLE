package com.munggle.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateDto {

    @Size(max = 50)
    @NotBlank
    @JsonProperty("email")
    private final String username;

    @Size(min = 8, max = 15)
    @NotBlank
    private final String password;

    @Size(min = 2, max = 10)
    @NotNull
    private final String nickname;
}