package com.minwoo.mysql.domain.post.controller;

import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostWriteService postWriteService;

    @PostMapping("/posts")
    public PostDto create(@RequestBody PostRegisterCommand command) {
        return postWriteService.create(command);
    }

}
