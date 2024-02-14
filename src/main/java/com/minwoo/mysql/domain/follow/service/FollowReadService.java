package com.minwoo.mysql.domain.follow.service;

import com.minwoo.mysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowReadService {

    private final FollowRepository followRepository;

}
