package com.munggle.user.repository;

import com.munggle.domain.model.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    void deleteByImageName(String imageName);
}
