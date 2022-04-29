package com.lawming.domain.repository;

import com.lawming.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void jpaTest() throws Exception {
        //given
        Member member = new Member();
        member.setLoginId("test3");
        member.setPassword("qwe123");
        member.setName("엠도3");
        member.setEmailAddress("test@baver.coin");
        member.setPhoneNumber("123-46-79");

        Member save = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findById(save.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(save.getId());

        //when

        //then
    }

}