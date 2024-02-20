package com.minwoo.mysql.domain.post.repository;

import com.minwoo.mysql.domain.post.entity.PostLike;
import com.minwoo.mysql.domain.post.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Long countByPostId(Long id);

    List<PostLike> findAllByMemberId(Long memberId);

    @Query(value = """
                   SELECT * 
                   FROM PostLike
                   WHERE memberId = :memberId
                   ORDER BY id DESC
                   LIMIT :size
                   """
            , nativeQuery = true)
    List<PostLike> findAllByTimelineWhenNoKey(@Param("memberId") Long memberId,
                                              @Param("size") Long size);

    @Query(value = """
                   SELECT *
                   FROM PostLike
                   WHERE memberId = :memberId
                   AND id < :key
                   ORDER BY id DESC
                   LIMIT :size
                   """
            , nativeQuery = true)
    List<PostLike> findAllByTimelineWithKey(@Param("memberId") Long memberId,
                                            @Param("size") Long size,
                                            @Param("key") Long key);

}
