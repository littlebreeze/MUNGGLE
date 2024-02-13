package com.munggle.dm.service;

import com.munggle.dm.dto.DMRoomDto;
import com.munggle.dm.repository.DMRoomRepository;
import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DMRoomServiceImpl implements DMRoomService {

    private final DMRoomRepository dmRoomRepository;

    @Override
    public List<DMRoomDto> getDMRoomList(Long id) {
        return dmRoomRepository.findByUsers_Id(id)
                .stream()
                .map(room -> DMRoomDto.builder()
                        .roomId(room.getId())
                        .userNickname(room.getUsers().stream()
                                .filter(user -> !user.getId().equals(id))
                                .findFirst()
                                .map(User::getNickname)
                                .orElse(null))
                        .profileImage(room.getUsers().stream()
                                .filter(user -> !user.getId().equals(id))
                                .findFirst()
                                .map(user -> Optional.ofNullable(user.getProfileImage())
                                        .map(UserImage::getImageURL)
                                        .orElse(null))
                                .orElse(null))
                        .build())
                .collect(Collectors.toList());
    }
}
