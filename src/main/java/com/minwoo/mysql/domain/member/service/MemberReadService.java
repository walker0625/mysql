package com.minwoo.mysql.domain.member.service;

import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.dto.response.MemberHistoryDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.entity.MemberNicknameHistory;
import com.minwoo.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.minwoo.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto findMember(Long id) {
        return memberRepository.findById(id).orElseThrow().toDto();
    }

    public List<MemberHistoryDto> findAllHistoryByMemberId(Long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId)
                .stream()
                .map(MemberNicknameHistory::toDto)
                .toList();
    }

    public List<MemberDto> findMembers(List<Long> ids) {
        return memberRepository.findAllByIdIn(ids)
                .stream()
                .map(Member::toDto)
                .toList();
    }

}
