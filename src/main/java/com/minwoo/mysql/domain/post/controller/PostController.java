package com.minwoo.mysql.domain.post.controller;

import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.service.PostReadService;
import com.minwoo.mysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    @PostMapping("/posts")
    public PostDto create(PostRegisterCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/posts/count")
    public List<DailyPostCountDto> getCount(PostCountRequest postCountRequest) {
        return postReadService.getDailyPostCount(postCountRequest);
    }

    @GetMapping("/posts/members/{memberId}")
    public Page<PostDto> getPosts(@PathVariable Long memberId, Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

}