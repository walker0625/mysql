package com.minwoo.mysql.domain.post.repository;

import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {

    List<Timeline> findAllByMemberId(Long memberId);

    @Query(value = """
                   SELECT * 
                   FROM timeline
                   WHERE memberId = :memberId
                   ORDER BY id DESC
                   LIMIT :size
                   """
            , nativeQuery = true)
    List<Timeline> findAllByTimelineWhenNoKey(@Param("memberId") Long memberId,
                                              @Param("size") Long size);

    @Query(value = """
                   SELECT *
                   FROM timeline
                   WHERE memberId = :memberId
                   AND id < :key
                   ORDER BY id DESC
                   LIMIT :size
                   """
            , nativeQuery = true)
    List<Timeline> findAllByTimelineWithKey(@Param("memberId") Long memberId,
                                            @Param("size") Long size,
                                            @Param("key") Long key);

}
