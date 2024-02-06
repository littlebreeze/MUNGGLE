package com.munggle.user.repository;

import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsEnabledTrue(Long id);

    Optional<User> findByUsernameAndIsEnabledTrue(String username);

    List<User> findByNicknameContainingAndIsEnabledTrue(String keyword);

    Optional<Object> findByNicknameAndIsEnabledTrue(String nickname);
}