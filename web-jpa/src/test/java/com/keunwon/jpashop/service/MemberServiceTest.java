package com.keunwon.jpashop.service;

import com.keunwon.jpashop.domain.Member;
import com.keunwon.jpashop.repository.MemberRepositoryOld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepositoryOld memberRepositoryOld;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepositoryOld.findOne(savedId));
    }

    @Test(expected = IllegalAccessException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 합니다.");
    }
}