package com.minwoo.mysql.domain.post.dto.request;

import java.util.Optional;

public record CursorRequest(
        Long key,
        Long size
) {

    public CursorRequest next(Long key) {
        return new CursorRequest(key, this.size);
    }

}
