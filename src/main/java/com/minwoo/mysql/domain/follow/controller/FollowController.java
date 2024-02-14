package com.minwoo.mysql.domain.follow.controller;

import com.minwoo.mysql.usecase.CreateFollowMemberUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;

    @PostMapping("/follow/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

}
