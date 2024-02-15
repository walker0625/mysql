package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.follow.service.FollowReadService;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMemberUsecase {

    private final FollowReadService followReadService;
    private final MemberReadService memberReadService;

    public List<MemberDto> getFollows(Long memberId) {
        List<Follow> follows = followReadService.getFollows(memberId);
        List<Long> followsIds = follows.stream().map(Follow::getToMemberId).toList();

        return memberReadService.findMembers(followsIds);
    }

}
