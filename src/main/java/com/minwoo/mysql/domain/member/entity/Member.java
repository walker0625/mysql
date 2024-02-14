package com.minwoo.mysql.domain.member.entity;

import com.minwoo.mysql.domain.member.dto.response.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@ToString
@Getter
@NoArgsConstructor(force = true)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String email;
    private String nickname;
    private final LocalDate birthday;
    private final LocalDateTime createdAt;

    private final static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String email, String nickname, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        validateTenLength(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public void validateTenLength(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "닉네임은 10자를 넘을 수 없습니다.");
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                            .id(this.id)
                            .email(this.email)
                            .nickname(this.nickname)
                            .birthday(this.birthday)
                        .build();
    }

}