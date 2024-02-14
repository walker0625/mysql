package com.minwoo.mysql.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberHistoryDto(
        Long id,
        Long memberId,
        String nickname,
        LocalDateTime createdAt
) {
}
