package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

}
