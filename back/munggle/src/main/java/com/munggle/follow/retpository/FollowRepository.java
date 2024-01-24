package com.munggle.follow.retpository;

import com.munggle.domain.model.entity.Follow;
import com.munggle.domain.model.entity.FollowId;
import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    List<Follow> findByTargetUserIdAndIsFollowedTrue(Long id);

    List<Follow> findByFollowUserIdAndIsFollowedTrue(Long id);
}
