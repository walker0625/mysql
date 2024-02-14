package com.minwoo.mysql.domain.member.entity;

import lombok.Builder;

import java.time.LocalDateTime;

public class Member {

    private final Long id;
    private String email;
    private String nickname;
    private final LocalDateTime birthday;
    private final LocalDateTime createAt;

    @Builder
    public Member(Long id, String email, String nickname, LocalDateTime birthday, LocalDateTime createAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
        this.createAt = createAt;
    }

}