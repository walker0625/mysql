package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.entity.PostLike;
import com.minwoo.mysql.domain.post.repository.PostLikeRepository;
import com.minwoo.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

    public void create(Post post, MemberDto memberDto) {

        PostLike postLike = PostLike.builder()
                                .postId(post.getId())
                                .memberId(memberDto.id())
                            .build();

        postLikeRepository.save(postLike);
    }

}
