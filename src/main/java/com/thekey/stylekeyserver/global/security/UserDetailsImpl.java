package com.thekey.stylekeyserver.global.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final AuthEntity authEntity;

    public UserDetailsImpl(AuthEntity authEntity){
        this.authEntity = authEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = authEntity.getUserId().toString();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    public AuthEntity getAuthEntity(){
        return authEntity;
    }

    @Override
    public String getUsername() {
        return authEntity.getUserId();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}

