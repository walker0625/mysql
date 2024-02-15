package com.minwoo.mysql.domain.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import com.minwoo.mysql.domain.post.entity.Post;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostJdbcTemplateRepository {

    static final String TABLE = "Post";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void bulkInsert(List<Post> posts) {
        var sql = String.format("""
                                INSERT INTO `%s` (memberId, contents, createdDate, createdAt)
                                VALUES (:memberId, :contents, :createdDate, :createdAt)
                                """, TABLE);

        SqlParameterSource[] params = posts
            .stream()
            .map(BeanPropertySqlParameterSource::new)
            .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

}