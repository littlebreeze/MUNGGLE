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
public class MessageDto {

    private Long roomId;

    private String senderNickname;

    private String receiverNickname;

    private String message;

    private LocalDateTime createAt;
}
