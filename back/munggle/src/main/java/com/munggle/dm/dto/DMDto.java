package com.munggle.dm.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DMDto {

    @Setter
    private Long roomId;

    private Long senderId;

    private Long receiver;

    private String message;
}
