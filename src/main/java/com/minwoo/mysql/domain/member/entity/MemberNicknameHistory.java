package com.minwoo.mysql.domain.member.entity;

import com.minwoo.mysql.domain.member.dto.response.MemberHistoryDto;
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
public class MemberNicknameHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final Long memberId;
    private final String nickname;
    private final LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static MemberNicknameHistory toEntity(Member member) {
        return MemberNicknameHistory.builder()
                                        .memberId(member.getId())
                                        .nickname(member.getNickname())
                                    .build();
    }

    public static MemberHistoryDto toDto(MemberNicknameHistory memberNicknameHistory) {
        return MemberHistoryDto.builder()
                                    .id(memberNicknameHistory.getId())
                                    .memberId(memberNicknameHistory.getMemberId())
                                    .nickname(memberNicknameHistory.getNickname())
                                    .createdAt(memberNicknameHistory.getCreatedAt())
                               .build();
    }

}
