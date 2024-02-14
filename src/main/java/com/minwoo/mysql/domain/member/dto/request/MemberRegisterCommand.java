package com.minwoo.mysql.domain.member.dto.request;

import java.time.LocalDate;

public record MemberRegisterCommand(
        String email,
        String nickname,
        LocalDate birthday
) {
}
