package com.munggle.post.repository;

import com.munggle.domain.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagNm(String hashtag);

    @Query("select t from Tag t " +
            "left join PostTag pt on t.id = pt.tag.id " +
            "where t.tagNm like concat('%', :word, '%') " +
            "group by t.id " +
            "order by count(pt) desc")
    List<Tag> searchByTagNm(@Param("word") String word);

}
