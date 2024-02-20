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
@Transactional
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

        Member savedMember = memberRepository.save(member);
        //int error = 1/0; // ArithmeticException 발생
        memberNicknameHistoryRepository.save(MemberNicknameHistory.toEntity(savedMember));

        return savedMember.toDto();
    }

    public MemberDto updateMember(Long id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.updateNickname(nickname); // update db

        memberNicknameHistoryRepository.save(MemberNicknameHistory.toEntity(member));

        return member.toDto();
    }

}