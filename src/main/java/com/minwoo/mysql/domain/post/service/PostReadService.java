package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public List<DailyPostCountDto> getDailyPostCount(PostCountRequest postCountRequest) {
        return postRepository.countByMemberIdAndCreatedDateBetween(postCountRequest.memberId(), postCountRequest.startDate(), postCountRequest.endDate());
    }

    public Page<PostDto> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable).map(Post::toDto);
    }

    public PageCursor<PostDto> getPostsByCursor(Long memberId, CursorRequest cursorRequest) {
        List<PostDto> postDtos;

        if(cursorRequest.key() == null) {
            postDtos = postRepository.findAllByMemberWhenNoKey(memberId, cursorRequest.size())
                                     .stream().map(Post::toDto).toList();
        } else {
            postDtos = postRepository.findAllByMemberWithKey(memberId, cursorRequest.size(), cursorRequest.key())
                                     .stream().map(Post::toDto).toList();
        }

        return new PageCursor<>(cursorRequest.next(postDtos.stream().map(PostDto::id).min(Long::compareTo).orElse(null)), postDtos);
    }

}