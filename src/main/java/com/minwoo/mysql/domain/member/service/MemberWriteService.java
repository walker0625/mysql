package com.minwoo.mysql.domain.member.service;

import com.minwoo.mysql.domain.member.dto.request.MemberRegisterCommand;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.entity.MemberNicknameHistory;
import com.minwoo.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.minwoo.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto register(MemberRegisterCommand command) {

        Member member = Member.builder()
                                .email(command.email())
                                .nickname(command.nickname())
                                .birthday(command.birthday())
                              .build();

        return memberRepository.save(member).toDto();
    }

    @Transactional
    public MemberDto updateMember(Long id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.updateNickname(nickname); // update db

        memberNicknameHistoryRepository.save(MemberNicknameHistory.toEntity(member));

        return member.toDto();
    }

}