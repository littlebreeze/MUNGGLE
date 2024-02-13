package com.munggle.dm.service;

import com.munggle.dm.dto.DMDto;
import com.munggle.dm.dto.MessageDto;
import com.munggle.dm.repository.ChatMessageRepository;
import com.munggle.dm.repository.DMRoomRepository;
import com.munggle.domain.exception.RoomNotFoundException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.ChatMessage;
import com.munggle.domain.model.entity.DMRoom;
import com.munggle.domain.model.entity.User;
import com.munggle.user.repository.UserRepository;
import com.munggle.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.ROOM_NOT_FOUND;
import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final DMRoomRepository dmRoomRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long findRoomByUsers(Long sendId, Long receiverId) {
        User sender = userRepository.findByIdAndIsEnabledTrue(sendId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        User receiver = userRepository.findByIdAndIsEnabledTrue(receiverId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return dmRoomRepository.findByUsersIn(sender, receiver)
                .map(DMRoom::getId)
                .orElseGet(() -> createRoom(sender, receiverId));
    }

    @Override
    @Transactional
    public Long createRoom(User user, Long otherUserId) {
        User otherUser = userRepository.findByIdAndIsEnabledTrue(otherUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        // userGroup에 User 객체 추가
        DMRoom dmRoom = DMRoom.builder()
                .users(new HashSet<>(Arrays.asList(user, otherUser)))
                .build();

        DMRoom savedRoom = dmRoomRepository.save(dmRoom); // DMRoom 저장

        return savedRoom.getId();
    }

    @Override
    @Transactional
    public void saveMessage(DMDto dmDto) {
        User sender = userRepository.findById(dmDto.getSenderId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        DMRoom room = dmRoomRepository.findById(dmDto.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(ROOM_NOT_FOUND));

        ChatMessage chatMessage = ChatMessage.builder()
                .dmId(room)
                .sendUser(sender)
                .content(dmDto.getMessage())
                .createAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        chatMessageRepository.save(chatMessage);

        room.getChatMessages().add(chatMessage);
        dmRoomRepository.save(room);
    }

    @Override
    public List<MessageDto> getDMListInRoom(Long roomId) {
        DMRoom room = dmRoomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(ROOM_NOT_FOUND));
        List<ChatMessage> messages = room.getChatMessages();

        return messages.stream()
                .map(message -> MessageDto.builder()
                        .roomId(roomId)
                        .senderNickname(userService.getNicknameById(message.getSendUser().getId()))
                        .receiverNickname(room.getUsers().stream()
                                .filter(user -> !user.getId().equals(message.getSendUser().getId()))
                                .findFirst()
                                .map(user -> userService.getNicknameById(user.getId()))
                                .orElse(null))
                        .message(message.getContent())
                        .createAt(message.getCreateAt())
                        .build())
                .collect(Collectors.toList());
    }
}