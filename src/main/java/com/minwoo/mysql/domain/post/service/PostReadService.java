package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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

}
