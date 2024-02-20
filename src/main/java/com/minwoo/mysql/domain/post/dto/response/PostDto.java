package com.minwoo.mysql.domain.post.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostDto(
        Long id,
        Long memberId,
        String contents,
        Long likeCount,
        LocalDate createdDate
) {
}
