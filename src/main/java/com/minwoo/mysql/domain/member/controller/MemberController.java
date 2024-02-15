package com.minwoo.mysql.domain.member.controller;

import com.minwoo.mysql.domain.member.dto.request.MemberRegisterCommand;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.dto.response.MemberHistoryDto;
import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import com.minwoo.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/members")
    public List<MemberDto> findMembers(@RequestParam List<Long> ids) {
        return memberReadService.findMembers(ids);
    }

    @PatchMapping("/members/{id}")
    public MemberDto updateMember(@PathVariable Long id, @RequestBody String nickname) {
        return memberWriteService.updateMember(id, nickname);
    }

    @GetMapping("/members/{id}/nickname-histories")
    public List<MemberHistoryDto> getMemberHistoryList(@PathVariable Long id) {
        return memberReadService.findAllHistoryByMemberId(id);
    }

}