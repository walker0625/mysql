package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.follow.service.FollowReadService;
import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Timeline;
import com.minwoo.mysql.domain.post.service.PostReadService;
import com.minwoo.mysql.domain.post.service.TimelineReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        List<Long> followIds = followReadService.getFollows(memberId)
                               .stream().map(Follow::getToMemberId).toList();

        return postReadService.getPostsByCursorAndMemberIds(followIds, cursorRequest);
    }

    // fan out(read - pull(정합성 높음/follow가 많을 경우 속도 저하) > facebook)
    public PageCursor<PostDto> executeByFollows(Long memberId, CursorRequest cursorRequest) {
        List<Long> followIds = followReadService.getFollows(memberId)
                               .stream().map(Follow::getToMemberId).toList();

        return postReadService.getPostsByCursorAndMemberIds(followIds, cursorRequest);
    }

    // fan out(write - push(follw 수에 비교적 성능 영향 적음/정합성 문제 - transaction 고려) > twitter)
    public PageCursor<PostDto> executeByTimeLines(Long memberId, CursorRequest cursorRequest) {
        PageCursor<Timeline> timelinesByCursor = timelineReadService.getTimelinesByCursor(memberId, cursorRequest);
        List<Long> postIds = timelinesByCursor.contents().stream().map(Timeline::getPostId).toList();
        List<PostDto> posts = postReadService.getPostsByIdIn(postIds);

        return new PageCursor<>(timelinesByCursor.cursorRequest(), posts);
    }

}