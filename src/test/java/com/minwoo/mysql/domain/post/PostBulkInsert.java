package com.minwoo.mysql.domain.post;

import com.minwoo.mysql.domain.post.entity.Post;
import com.minwoo.mysql.domain.post.repository.PostJdbcTemplateRepository;
import com.minwoo.mysql.util.PostFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

//@SpringBootTest
public class PostBulkInsert {

    //@Autowired
    private PostJdbcTemplateRepository postJdbcTemplateRepository;

   //@Test
   void bulkInsert() {
       EasyRandom easyRandom = PostFixtureFactory.get(4L,
               LocalDate.of(1970, 1, 1),
               LocalDate.of(2024, 2, 1));

       StopWatch stopWatch = new StopWatch();

       stopWatch.start();
       int oneman = 10000;
       List<Post> posts = IntStream.range(0, oneman * 100)
                                   .parallel()
                                   .mapToObj(i -> easyRandom.nextObject(Post.class))
                                   .toList();
       stopWatch.stop();

       System.out.println("create : " + stopWatch.getTotalTimeSeconds());

       //////////////

       StopWatch queryStopWatch = new StopWatch();

       queryStopWatch.start();
       postJdbcTemplateRepository.bulkInsert(posts);
       queryStopWatch.stop();

       System.out.println("save : " + queryStopWatch.getTotalTimeSeconds());
   }

}
