package com.minwoo.mysql.domain.member.entity;

import com.minwoo.mysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void test() {
        Member member = MemberFixtureFactory.create();
        String chName = "walker";

        member.updateNickname(chName);

        Assertions.assertEquals(chName, member.getNickname());
    }

    @Test
    void test10() {
        Member member = MemberFixtureFactory.create();
        String chName = "walker11111";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> member.updateNickname(chName)
        );
    }



}