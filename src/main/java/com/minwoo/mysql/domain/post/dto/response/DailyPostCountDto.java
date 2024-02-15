package com.minwoo.mysql.domain.post.dto.response;

import java.time.LocalDate;

public record DailyPostCountDto(
    Long memberId,
    LocalDate date,
    Long postCount) {
}
