package com.lawming.web.auth;

import com.lawming.domain.member.Member;
import com.lawming.domain.repository.MemberJpaRepository;
import com.lawming.domain.repository.MemberRepository;
import com.lawming.web.model.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행됨
@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberJpaRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member =  memberRepository.findByLoginId(username);

        if(member != null) {

            return new PrincipalDetails(new LoginForm(member.getId(),
                    member.getLoginId(), member.getPassword(),
                    member.getEmailAddress(), member.getRole()));
        }

        return null;
    }
}
