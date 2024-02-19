package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.entity.Timeline;
import com.minwoo.mysql.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {

    private final TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds) {
        List<Timeline> timelines = toMemberIds.stream().map((toMemberId) ->
                Timeline.builder().memberId(toMemberId).postId(postId).build())
                .toList();

        timelineRepository.saveAll(timelines); // bulkInsert better
    }

}
