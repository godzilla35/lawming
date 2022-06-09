package com.lawming.web.auth;

import com.lawming.web.model.login.LoginForm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private LoginForm logInMember;

    public PrincipalDetails(LoginForm logInMember) {
        this.logInMember = logInMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return logInMember.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return logInMember.getPassword();
    }

    @Override
    public String getUsername() {
        return logInMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
