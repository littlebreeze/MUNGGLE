package com.munggle.dm.service;

import com.munggle.dm.dto.DMRoomDto;
import com.munggle.domain.model.entity.DMRoom;

import java.util.List;

public interface DMRoomService {
    List<DMRoomDto> getDMRoomList(Long id);
}
