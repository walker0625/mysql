package com.minwoo.mysql.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {
}
