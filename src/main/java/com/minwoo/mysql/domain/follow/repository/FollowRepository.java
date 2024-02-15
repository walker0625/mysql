package com.minwoo.mysql.domain.follow.repository;

import com.minwoo.mysql.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFromMemberId(Long memberId);

}
