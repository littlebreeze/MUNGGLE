package com.munggle.dm.repository;

import com.munggle.domain.model.entity.DMRoom;
import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DMRoomRepository extends JpaRepository<DMRoom, Long> {

    @Query("SELECT r FROM DMRoom r WHERE :user1 MEMBER OF r.users AND :user2 MEMBER OF r.users")
    Optional<DMRoom> findByUsersIn(@Param("user1") User user1, @Param("user2") User user2);

    List<DMRoom> findByUsers_Id(Long userId);

}
