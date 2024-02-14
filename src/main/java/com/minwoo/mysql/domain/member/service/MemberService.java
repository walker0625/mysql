package com.minwoo.mysql.domain.member.service;

import com.minwoo.mysql.domain.member.dto.MemberRegistDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(MemberRegistDto memberRegistDto) {
        return memberRepository.saveMember(memberRegistDto);
    }

}
