package com.minwoo.mysql.domain.member.controller;

import com.minwoo.mysql.domain.member.dto.request.MemberRegisterCommand;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import com.minwoo.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody MemberRegisterCommand command) {
        return memberWriteService.register(command);
    }

    @GetMapping("/members/{id}")
    public MemberDto findMember(@PathVariable Long id) {
        return memberReadService.findMember(id);
    }

}
