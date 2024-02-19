package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.follow.service.FollowReadService;
import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        List<Long> followIds = followReadService.getFollows(memberId)
                               .stream().map(Follow::getToMemberId).toList();

        return postReadService.getPostsByCursorAndMemberIds(followIds, cursorRequest);
    }

}
