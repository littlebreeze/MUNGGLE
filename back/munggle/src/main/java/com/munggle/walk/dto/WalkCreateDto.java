package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkCreateDto {

    private Long userId;
    private Long dogId;
    private boolean isDeleted;
}
