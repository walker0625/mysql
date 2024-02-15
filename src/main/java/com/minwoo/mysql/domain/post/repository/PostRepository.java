package com.minwoo.mysql.domain.post.repository;

import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT new com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto(p.memberId, p.createdDate AS date, COUNT(*) AS postCount) " +
                   "FROM Post p " +
                   "WHERE p.memberId = :memberId " +
                   "AND p.createdDate BETWEEN :startDate AND :endDate " +
                   "GROUP BY p.createdDate, p.memberId")
    List<DailyPostCountDto> countByMemberIdAndCreatedDateBetween(@Param("memberId") Long memberId,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate);

}
