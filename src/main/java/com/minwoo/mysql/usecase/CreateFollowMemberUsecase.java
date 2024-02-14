package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.follow.service.FollowWriteService;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        MemberDto fromMember = memberReadService.findMember(fromMemberId);
        MemberDto toMember = memberReadService.findMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }

}
