package com.lawming.server.repository;

import com.lawming.server.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepository {
    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Transactional
    public void update(Member memberParam) {
        Member getMember = em.find(Member.class, memberParam.getId());

        getMember.setEmailAddress(memberParam.getEmailAddress());
        getMember.setPassword(memberParam.getPassword());
        getMember.setPhoneNumber(memberParam.getPhoneNumber());
    }
}
