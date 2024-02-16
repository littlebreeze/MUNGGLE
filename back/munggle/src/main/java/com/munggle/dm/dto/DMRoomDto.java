package com.munggle.dm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DMRoomDto {

    private Long roomId;
    private Long otherUserId;
    private String userNickname;
    private String profileImage;
    private String lastContent;
    private LocalDateTime lastSendTime;
}
