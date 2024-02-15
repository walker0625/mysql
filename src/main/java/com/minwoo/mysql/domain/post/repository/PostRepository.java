package com.minwoo.mysql.domain.post.repository;

import com.minwoo.mysql.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
