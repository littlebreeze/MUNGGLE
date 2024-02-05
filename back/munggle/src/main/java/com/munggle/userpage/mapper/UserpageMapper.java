package com.munggle.userpage.mapper;

import com.munggle.domain.model.entity.Post;
import com.munggle.userpage.dto.UserPostListDto;
import com.munggle.userpage.dto.UserScrapListDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserpageMapper {
    public static UserPostListDto toUserPagePostList(Post post) {
        return UserPostListDto.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .imageURL(post.getPostImageList().get(0).getImageURL())
                .build();
    }

    public static UserScrapListDto toUserPageScrapList(Post post) {
        return UserScrapListDto.builder()
                .postId(post.getId())
                .imageURL(post.getPostImageList().get(0).getImageURL())
                .build();
    }
}
