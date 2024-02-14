package com.minwoo.mysql.domain.member.controller;

import com.minwoo.mysql.domain.member.dto.MemberRegistDto;
import com.minwoo.mysql.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public void saveMember(@RequestBody MemberRegistDto memberRegistDto) {
        memberService.saveMember(memberRegistDto);
    }

}
