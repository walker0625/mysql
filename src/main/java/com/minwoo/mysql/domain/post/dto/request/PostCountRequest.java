package com.minwoo.mysql.domain.post.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PostCountRequest(
    Long memberId,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate
) {
}
