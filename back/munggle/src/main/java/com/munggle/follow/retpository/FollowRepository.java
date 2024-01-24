package com.munggle.follow.retpository;

import com.munggle.domain.model.entity.Follow;
import com.munggle.domain.model.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
}
