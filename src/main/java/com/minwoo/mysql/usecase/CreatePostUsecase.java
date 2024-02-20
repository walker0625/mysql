package com.minwoo.mysql.usecase;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.follow.service.FollowReadService;
import com.minwoo.mysql.domain.post.dto.request.PostRegisterCommand;
import com.minwoo.mysql.domain.post.dto.response.PostDto;
import com.minwoo.mysql.domain.post.service.PostWriteService;
import com.minwoo.mysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostUsecase {

    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public void execute(PostRegisterCommand command) {
        PostDto postDto = postWriteService.create(command);
        List<Long> toMemberIds = followReadService.getFollows(postDto.memberId()).stream().map(Follow::getToMemberId).toList();
        timelineWriteService.deliveryToTimeline(postDto.id(), toMemberIds);
    }

}
