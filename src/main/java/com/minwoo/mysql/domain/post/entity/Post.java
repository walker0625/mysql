package com.minwoo.mysql.domain.post.entity;

import com.minwoo.mysql.domain.post.dto.response.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(force = true)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Long memberId;

    private final String contents;

    private Long likeCount; // 추가 컬럼

    private final LocalDate createdDate;

    private final LocalDateTime createdAt;

    @Version
    private Long version;

    @Builder
    public Post(Long id, Long memberId, String contents, Long likeCount, LocalDate createdDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.likeCount = likeCount == null ? 0 : likeCount;
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static PostDto toDto(Post post) {
        return PostDto.builder()
                            .id(post.getId())
                            .memberId(post.getMemberId())
                            .contents(post.getContents())
                            .likeCount(post.getLikeCount() == null ? 0 : post.getLikeCount())
                            .createdDate(post.getCreatedDate())
                      .build();
    }

    public void incrementLikeCount() {
        likeCount = likeCount == null ? 1 : likeCount + 1;
    }

}
