package com.minwoo.mysql.domain.post.entity;

import com.minwoo.mysql.domain.post.dto.response.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Long memberId;

    private final String contents;

    private final LocalDate createdDate;

    private final LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static PostDto toDto(Post post) {
        return PostDto.builder()
                            .id(post.getId())
                            .memberId(post.getMemberId())
                            .contents(post.getContents())
                            .createdDate(post.getCreatedDate())
                      .build();
    }

}
