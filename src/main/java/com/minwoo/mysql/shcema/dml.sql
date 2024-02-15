explain select createdDate, memberId, count(*)
        from post
        where post.memberId = 3
          and post.createdDate between "1970-01-01" and "2024-02-01"
        group by createdDate;

explain select createdDate, memberId, count(*)
        from post use index(POST__index_member_id)
        where post.memberId = 3
          and post.createdDate between "1970-01-01" and "2024-02-01"
        group by createdDate;

explain select createdDate, memberId, count(*)
        from post use index(POST__index_created_date)
        where post.memberId = 3
          and post.createdDate between "1970-01-01" and "2024-02-01"
        group by createdDate;

explain select createdDate, memberId, count(*)
        from post use index(POST__index_member_id_created_date)
        where post.memberId = 3
          and post.createdDate between "1970-01-01" and "2024-02-01"
        group by createdDate;