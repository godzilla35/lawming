package com.lawming.domain.repository;

import com.lawming.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    public Member findByLoginId(String loginId);
}
