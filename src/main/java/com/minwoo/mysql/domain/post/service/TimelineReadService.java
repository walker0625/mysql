package com.minwoo.mysql.domain.post.service;

import com.minwoo.mysql.domain.post.dto.request.CursorRequest;
import com.minwoo.mysql.domain.post.dto.response.PageCursor;
import com.minwoo.mysql.domain.post.entity.Timeline;
import com.minwoo.mysql.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineReadService {

    private final TimelineRepository timelineRepository;

    public List<Timeline> getTimelines(Long memberId){
        return timelineRepository.findAllByMemberId(memberId);
    }

    public PageCursor<Timeline> getTimelinesByCursor(Long memberId, CursorRequest cursorRequest) {
        List<Timeline> timelines;

        if (cursorRequest.key() == null) {
            timelines = timelineRepository.findAllByTimelineWhenNoKey(memberId, cursorRequest.size());
        } else {
            timelines = timelineRepository.findAllByTimelineWithKey(memberId, cursorRequest.size(), cursorRequest.key());
        }

        long key = timelines.stream().mapToLong(Timeline::getId).min().orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(key), timelines);
    }

}
