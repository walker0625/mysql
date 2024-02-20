package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.request.PostCountRequest;
import com.minwoo.mysql.domain.post.dto.response.DailyPostCountDto;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.repository.PostLikeRepository;
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
    private final PostLikeRepository postLikeRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public List<PostDto> getPostsByIdIn(List<Long> postIds) {
        return postRepository.findAllByIdIn(postIds).stream().map(Post::toDto).toList();
    }

    public List<DailyPostCountDto> getDailyPostCount(PostCountRequest postCountRequest) {
        return postRepository.countByMemberIdAndCreatedDateBetween(postCountRequest.memberId(), postCountRequest.startDate(), postCountRequest.endDate());
    }

    public Page<PostDto> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable).map(this::toDtoService);
    }

    public PostDto toDtoService(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .contents(post.getContents())
                // 쓰기 성능을 위해 읽기 성능을 trade off
                // 대안 1. like 집계 스케쥴러로 1s 마다 countLike 컬럼 업데이트(like 테이블 사용 x)
                // 대안 2. redis/client cache 등을 활용
                .likeCount(postLikeRepository.countByPostId(post.getId()))
                .createdDate(post.getCreatedDate())
                .build();
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

        return new PageCursor<>(cursorRequest.next(postDtos.stream().map(PostDto::id).min(Long::compareTo).orElse(CursorRequest.NONE_KEY)), postDtos);
    }

    public PageCursor<PostDto> getPostsByCursorAndMemberIds(List<Long> memberIds, CursorRequest cursorRequest) {
        List<PostDto> postDtos;

        if(cursorRequest.key() == null) {
            postDtos = postRepository.findAllByInMembersWhenNoKey(memberIds, cursorRequest.size())
                    .stream().map(Post::toDto).toList();
        } else {
            postDtos = postRepository.findAllByInMembersWithKey(memberIds, cursorRequest.size(), cursorRequest.key())
                    .stream().map(Post::toDto).toList();
        }

        return new PageCursor<>(cursorRequest.next(postDtos.stream().map(PostDto::id).min(Long::compareTo).orElse(CursorRequest.NONE_KEY)), postDtos);
    }

}