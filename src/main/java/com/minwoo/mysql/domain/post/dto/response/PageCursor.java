package com.minwoo.mysql.domain.post.dto.response;

import com.minwoo.mysql.domain.post.dto.request.CursorRequest;

import java.util.List;

public record PageCursor<T>(
     CursorRequest cursorRequest,
     List<T> contents
) {
}
