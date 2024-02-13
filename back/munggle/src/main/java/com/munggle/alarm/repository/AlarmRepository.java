package com.munggle.alarm.repository;

import com.munggle.domain.model.entity.Alarm;
import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Integer countByToUserAndIsCheckedFalse(User user);

    List<Alarm> findByToUserOrderByCreatedAtDesc(User toUser);

    List<Alarm> findByToUserAndIsCheckedFalse(User toUser);

}
