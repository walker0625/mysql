package com.minwoo.mysql.domain.member.repository;

import com.minwoo.mysql.domain.member.dto.MemberRegistDto;
import com.minwoo.mysql.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    public Member saveMember(MemberRegistDto memberRegistDto) {
        return Member.builder()
                     .email(memberRegistDto.email())
                     .nickname(memberRegistDto.nickname())
                     .birthday(memberRegistDto.birthday())
                     .build();
    }

}
