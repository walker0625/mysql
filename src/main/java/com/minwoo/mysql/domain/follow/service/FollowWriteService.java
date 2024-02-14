package com.minwoo.mysql.domain.follow.service;

import com.minwoo.mysql.domain.follow.entity.Follow;
import com.minwoo.mysql.domain.follow.repository.FollowRepository;
import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.Member;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {

        Assert.isTrue(!fromMember.id().equals(toMember.id()), "본인을 follow 하는 것은 불가합니다");

        Follow follow = Follow.builder()
                                  .fromMemberId(fromMember.id())
                                  .toMemberId(toMember.id())
                              .build();

        followRepository.save(follow);
    }

}
