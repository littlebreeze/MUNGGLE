package com.munggle.member.repository;

import com.munggle.domain.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndIsDeletedFalse(Long id);

    Optional<Member> findByUsernameAndIsDeletedFalse(String username);
}