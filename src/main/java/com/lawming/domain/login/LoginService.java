package com.lawming.domain.login;

import com.lawming.domain.member.Member;
import com.lawming.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login (String loginId, String password) {
        List<Member> members = memberRepository.findByLoginId(loginId);
        if(members.isEmpty()) {
            return null;
        }

        if(members.size() > 1) {
            return null;
        }

        Member member = members.get(0);

        if(member.getPassword().equals(password)) {
            return member;
        } else
        {
            return null;
        }
    }

}
