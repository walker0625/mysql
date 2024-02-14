package com.minwoo.mysql.domain.member.service;

import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;

    public MemberDto findMember(Long id) {
        return memberRepository.findById(id).orElseThrow().toDto();
    }

}
