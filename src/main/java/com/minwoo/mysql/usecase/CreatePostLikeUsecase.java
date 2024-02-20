package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import com.minwoo.mysql.domain.member.service.MemberReadService;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.service.PostLikeWriteService;
import com.minwoo.mysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUsecase {

    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        Post post = postReadService.getPost(postId);
        MemberDto memberDto = memberReadService.findMember(memberId);
        postLikeWriteService.create(post, memberDto);
    }

}
