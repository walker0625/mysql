package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public PostDto create(PostRegisterCommand command) {

        Post post = Post.builder()
                            .memberId(command.memberId())
                            .contents(command.contents())
                        .build();

        return Post.toDto(postRepository.save(post));
    }
    
    public void likePost(Long postId) {
        Post post = postRepository.findAndLockById(postId);
        post.incrementLikeCount();
    }

}
