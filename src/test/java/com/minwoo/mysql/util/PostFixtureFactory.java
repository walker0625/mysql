package com.minwoo.mysql.util;

import com.minwoo.mysql.domain.member.entity.Member;
import com.minwoo.mysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {

    public static EasyRandom get(Long memberId, LocalDate startDate, LocalDate endDate) {
        Predicate<Field> idPredicate = named("id").and(ofType(Long.class)).and(inClass(Post.class));
        Predicate<Field> memberIdPredicate = named("memberId").and(ofType(Long.class)).and(inClass(Post.class));

        EasyRandomParameters param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(memberIdPredicate, () -> memberId)
                .dateRange(startDate, endDate);

        return new EasyRandom(param);
    }

}
