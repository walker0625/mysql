package com.minwoo.mysql.domain.post.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PostDto(
        Long id,
        Long memberId,
        String contents,
        LocalDate createdDate
) {
}
