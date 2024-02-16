package com.munggle.emailverification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    @Query(value = "SELECT * FROM email_verification WHERE email = :email AND expiration_time >= NOW() - INTERVAL 3 MINUTE ORDER BY expiration_time DESC LIMIT 1", nativeQuery = true)
    Optional<EmailVerification> findByEmail(String email);
    void deleteByEmail(String email);
}

