package com.munggle.post.service;

import com.munggle.domain.model.entity.UserRecentTagCache;
import com.munggle.post.dto.response.PostListDto;

import java.util.List;

public interface CuratingService {

//    void saveRecentTag(Long userId, Long tagId);
void saveRecentTag(Long userId, Long tagId);

    List<String> getTagList(Long userId);
    List<PostListDto> getPostCuratingList(Long userId);

}
