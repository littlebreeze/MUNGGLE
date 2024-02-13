package com.munggle.dm.controller;

import com.munggle.dm.dto.DMDto;
import com.munggle.dm.dto.DMRoomDto;
import com.munggle.dm.dto.MessageDto;
import com.munggle.dm.service.ChatMessageService;
import com.munggle.dm.service.DMRoomService;
import com.munggle.domain.model.entity.DMRoom;
import com.munggle.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class DMRoomController {

    private final ChatMessageService chatMessageService;
    private final DMRoomService dmRoomService;

    @GetMapping
    public List<DMRoomDto> getDMRoomList(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        return dmRoomService.getDMRoomList(id);
    }

    @GetMapping("/{roomId}")
    public List<MessageDto> getDMList(@AuthenticationPrincipal User principal,
                                      @PathVariable Long roomId) {
        Long id = principal.getId();
        return chatMessageService.getDMListInRoom(roomId);
    }
}
