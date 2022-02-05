package com.study.jpashop.service;

import com.study.jpashop.member.domain.Member;
import com.study.jpashop.member.repository.MemberRepository;
import com.study.jpashop.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("hwang");
        //when
        Long saveId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member, memberRepository.findTopById(saveId));
    }

    @Test
    public void 중복회원예외() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("hwang");

        Member member2 = new Member();
        member2.setName("hwang");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () ->
                memberService.join(member2)
        );
    }
}