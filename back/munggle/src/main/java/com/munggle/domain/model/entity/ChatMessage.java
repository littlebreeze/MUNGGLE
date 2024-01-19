package com.munggle.domain.model.entity;

import java.time.LocalDateTime;

public class ChatMessage {

    private Long id;

    private Long dmId;

    private Long fromUserId;

    private Long content;

    private LocalDateTime createAt;

    private boolean isDeleted;
}
