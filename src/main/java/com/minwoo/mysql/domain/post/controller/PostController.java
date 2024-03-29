package com.minwoo.mysql.domain.post.controller;

import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.service.PostReadService;
import com.minwoo.mysql.domain.post.service.PostWriteService;
import com.minwoo.mysql.usecase.CreatePostLikeUsecase;
import com.minwoo.mysql.usecase.CreatePostUsecase;
import com.minwoo.mysql.usecase.GetTimelinePostUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    private final GetTimelinePostUsecase getTimelinePostUsecase;
    private final CreatePostUsecase createPostUsecase;
    private final CreatePostLikeUsecase createPostLikeUsecase;

    @PostMapping("/posts")
    public PostDto create(PostRegisterCommand command) {
        return postWriteService.create(command);
    }

    @PostMapping("/posts/timelines")
    public void createWithTimeline(PostRegisterCommand command) {
        createPostUsecase.execute(command);
    }

    @GetMapping("/posts/count")
    public List<DailyPostCountDto> getCount(PostCountRequest postCountRequest) {
        return postReadService.getDailyPostCount(postCountRequest);
    }

    @GetMapping("/posts/members/{memberId}")
    public Page<PostDto> getPosts(@PathVariable Long memberId, Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/posts/members/{memberId}/cursor")
    public PageCursor<PostDto> getPostsByCursor(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return postReadService.getPostsByCursor(memberId, cursorRequest);
    }

    @GetMapping("/posts/members/{memberId}/timeline")
    public PageCursor<PostDto> getTimeLines(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return getTimelinePostUsecase.execute(memberId, cursorRequest);
    }

    @GetMapping("/posts/members/{memberId}/timeline/table")
    public PageCursor<PostDto> getTimeLinesByTable(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return getTimelinePostUsecase.executeByTimeLines(memberId, cursorRequest);
    }

    @PostMapping("/posts/{postId}/like")
    public void likePost(@PathVariable Long postId) {
        postWriteService.likePost(postId);
    }

    @PostMapping("/posts/{postId}/like/v2")
    public void likePostV2(@PathVariable Long postId, @RequestParam Long memberId) {
        createPostLikeUsecase.execute(postId, memberId);
    }

}