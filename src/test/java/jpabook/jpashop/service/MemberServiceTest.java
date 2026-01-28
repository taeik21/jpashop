package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("홍길동");

        //when
        Long memberId = memberService.join(member);
        Member findMember = memberService.findOne(memberId);

        //then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("홍길동");

        Member member2 = new Member();
        member2.setName("홍길동");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, ()->
                memberService.join(member2)
                );
    }
}