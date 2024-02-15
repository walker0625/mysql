package com.minwoo.mysql.domain.post.dto.request;

public record PostRegisterCommand(
        Long memberId,
        String contents
) {
}
