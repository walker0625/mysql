package com.minwoo.mysql.domain.member.repository;

import com.minwoo.mysql.domain.member.entity.MemberNicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long> {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);

}
