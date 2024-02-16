package com.munggle.dm.service;

import com.munggle.dm.dto.DMDto;
import com.munggle.dm.dto.DMRoomDto;
import com.munggle.dm.dto.MessageDto;
import com.munggle.domain.model.entity.User;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatMessageService {
    Long findRoomByUsers(Long sendId, Long receiverId);

    Long createRoom(User user, Long otherUserId);

    void saveMessage(DMDto dmDto);

    List<MessageDto> getDMListInRoom(Long roomId);
}
