package com.minwoo.mysql.domain.follow.controller;

import com.minwoo.mysql.application.usecase.CreateFollowMemberUsecase;
import com.minwoo.mysql.domain.follow.service.FollowWriteService;
import com.minwoo.mysql.domain.member.dto.request.MemberRegisterCommand;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.dto.response.MemberHistoryDto;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import com.minwoo.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;

    @PostMapping("/follow/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

}
