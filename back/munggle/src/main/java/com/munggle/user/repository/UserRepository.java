package com.munggle.user.repository;

import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsEnabledFalse(Long id);

    Optional<User> findByUsernameAndIsEnabledFalse(String username);
}