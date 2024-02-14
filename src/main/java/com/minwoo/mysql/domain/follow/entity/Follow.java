package com.minwoo.mysql.domain.follow.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(force = true)
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Long fromMemberId;

    private final Long toMemberId;

    private final LocalDateTime createdAt;

    @Builder
    public Follow(Long id, Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.id = id;
        this.fromMemberId = Objects.requireNonNull(fromMemberId);
        this.toMemberId =   Objects.requireNonNull(toMemberId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

}
