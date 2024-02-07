package com.munggle.follow.retpository;

import com.munggle.domain.model.entity.Follow;
import com.munggle.domain.model.entity.FollowId;
import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    List<Follow> findByFollowToIdAndIsFollowedTrue(Long id);

    List<Follow> findByFollowFromIdAndIsFollowedTrue(Long id);

    Integer countByFollowToIdAndIsFollowedTrue(Long id);

    Integer countByFollowFromIdAndIsFollowedTrue(Long userId);
}
