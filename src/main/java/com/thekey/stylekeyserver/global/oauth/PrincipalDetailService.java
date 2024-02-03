package com.thekey.stylekeyserver.global.oauth;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.global.oauth.dto.SessionUser;

@Primary
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository repository;

    public PrincipalDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users principal = repository.findByEmail(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
                });
        return new SessionUser(principal);
    }
}