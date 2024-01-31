package com.munggle.post.repository;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.UserRecentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRecentTagRepository extends JpaRepository<UserRecentTag, Long> {

    @Modifying
    @Query("delete from UserRecentTag ur where ur.user = :user")
    void deleteByUser(User user);
}
