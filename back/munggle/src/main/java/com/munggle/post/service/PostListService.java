package com.munggle.post.service;

import com.munggle.domain.model.entity.UserRecentTagCache;
import com.munggle.post.dto.response.PagePostDto;
import com.munggle.post.dto.response.PostListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostListService {

    void saveRecentTag(Long userId, Long tagId);

    List<String> getTagList(Long userId);

    List<PostListDto> getPostCuratingList(Long userId);

    PagePostDto getFollowingPost(Long userId, Pageable pageable);

}
