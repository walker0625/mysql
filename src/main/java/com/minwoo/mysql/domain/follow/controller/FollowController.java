package com.minwoo.mysql.domain.follow.controller;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.usecase.CreateFollowMemberUsecase;
import com.minwoo.mysql.usecase.GetFollowingMemberUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;
    private final GetFollowingMemberUsecase getFollowingMemberUsecase;

    @PostMapping("/follows/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/follows/members/{memberId}")
    public List<MemberDto> getFollows(@PathVariable Long memberId) {
        return getFollowingMemberUsecase.getFollows(memberId);
    }

}
