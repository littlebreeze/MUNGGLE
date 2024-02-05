package com.munggle.userpage.service;

import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.dog.repository.DogRepository;
import com.munggle.domain.exception.DogNotFoundException;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.NotAllowAccessPageException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.User;
import com.munggle.post.repository.ScrapRepository;
import com.munggle.userpage.dto.UserPostListDto;
import com.munggle.post.repository.PostRepository;
import com.munggle.user.repository.UserRepository;
import com.munggle.userpage.dto.UserScrapListDto;
import com.munggle.userpage.mapper.UserpageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.NOT_ALLOW_PAGE;
import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserpageServiceImpl implements UserpageService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ScrapRepository scrapRepository;
    private final DogRepository dogRepository;

    /**
     * 유저페이지에서 해당 유저의 모든 게시글을 불러오는 메소드
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserPostListDto> getUserPost(Long findUserId, Long userId) {

        List<Post> userPostList;

        // 존재하지 않은 유저의 페이지를 찾을 경우 에러 반환
        userRepository.findByIdAndIsEnabledTrue(findUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (findUserId.equals(userId)) {
            userPostList = postRepository.findByUserIdAndIsDeletedFalse(findUserId);
        } else {
            userPostList = postRepository.findByUserIdAndIsDeletedFalseAndIsPrivateFalse(findUserId);
        }

        return userPostList.stream()
                .map(UserpageMapper::toUserPagePostList)
                .collect(Collectors.toList());
    }

    /**
     * 스크랩한 게시글을 불러오는 메소드
     * 
     * @param findUserId
     * @param userId
     * @return
     */
    @Override
    public List<UserScrapListDto> getUserScrap(Long findUserId, Long userId) {

        List<Post> userScrapList;

        // 존재하지 않은 유저의 페이지를 찾을 경우 에러 반환
        userRepository.findByIdAndIsEnabledTrue(findUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (findUserId.equals(userId)) {
            userScrapList = scrapRepository.findByUserIdAndIsDeletedFalse(findUserId);
        } else {
            throw new NotAllowAccessPageException(NOT_ALLOW_PAGE);
        }

        return userScrapList.stream()
                .map(UserpageMapper::toUserPageScrapList)
                .collect(Collectors.toList());
    }

    /**
     * 유저의 강아지 리스트를 불러오는 메소드
     * 
     * @param userId
     * @return
     */
    @Override
    public List<DogDetailDto> getDogList(Long userId) {

        // 넘겨 받은 사용자가 있는지 확인
        User user = userRepository.findByIdAndIsEnabledTrue(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        List<Dog> list = dogRepository.findAllByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        return list.stream().map(dog -> DogMapper.toDetailDto(dog)).collect(Collectors.toList());
    }
}
