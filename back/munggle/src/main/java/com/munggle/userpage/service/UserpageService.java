package com.munggle.userpage.service;

import com.munggle.dog.dto.DogDetailDto;
import com.munggle.userpage.dto.UserCalendarDto;
import com.munggle.userpage.dto.UserPostListDto;
import com.munggle.userpage.dto.UserScrapListDto;

import java.util.List;

public interface UserpageService {
    List<UserPostListDto> getUserPost(Long findUserId, Long userId);

    List<UserScrapListDto> getUserScrap(Long findUserId, Long userId);

    List<DogDetailDto> getDogList(Long userId);

    List<UserCalendarDto> getUserCalendar(Long userId, Integer year, Integer month);

}
