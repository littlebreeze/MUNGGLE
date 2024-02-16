package com.munggle.follow.repository;

import com.munggle.domain.model.entity.Follow;
import com.munggle.domain.model.entity.FollowId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    Page<Follow> findByFollowToIdAndIsFollowedTrue(Long id, Pageable pageable);

    Page<Follow> findByFollowFromIdAndIsFollowedTrue(Long id, Pageable pageable);

    boolean existsByFollowFromIdAndFollowToIdAndIsFollowedTrue(Long followerId, Long targetId);

    List<Follow> findByFollowFromIdAndIsFollowedTrue(Long id);

    List<Follow> findByFollowToIdAndIsFollowedTrue(Long id);

    Integer countByFollowToIdAndIsFollowedTrue(Long id);

    Integer countByFollowFromIdAndIsFollowedTrue(Long userId);
}
