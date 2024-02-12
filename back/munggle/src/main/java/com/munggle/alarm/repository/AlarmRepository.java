package com.munggle.alarm.repository;

import com.munggle.alarm.dto.AlarmDetailDto;
import com.munggle.domain.model.entity.Alarm;
import com.munggle.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByToUserIdAndIsDeletedFalse(User toUser);
}
