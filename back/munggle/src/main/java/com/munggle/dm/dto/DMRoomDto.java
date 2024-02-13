package com.munggle.dm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DMRoomDto {

    private Long roomId;
    private String userNickname;
    private String profileImage;
}
