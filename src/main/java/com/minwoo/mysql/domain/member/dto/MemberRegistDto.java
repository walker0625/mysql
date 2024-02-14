package com.minwoo.mysql.domain.member.dto;

import java.time.LocalDateTime;

public record MemberRegistDto(
        String email,
        String nickname,
        LocalDateTime birthday
) {
}
