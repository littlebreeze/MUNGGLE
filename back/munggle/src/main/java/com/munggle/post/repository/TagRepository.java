package com.munggle.post.repository;

import com.munggle.domain.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagNm(String hashtag);

//    @Query("select t from Tag t where t.tagNm like concat('%', :word, '%')")
    @Query("SELECT t FROM Tag t " +
            "LEFT JOIN PostTag pt ON t.id = pt.tag.id " +
            "WHERE t.tagNm LIKE concat('%', :word, '%') " +
            "GROUP BY t.id " +
            "ORDER BY COUNT(pt) DESC")
    List<Tag> searchByTagNm(@Param("word") String word);

}
