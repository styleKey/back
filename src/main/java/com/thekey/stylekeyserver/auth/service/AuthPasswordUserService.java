package com.thekey.stylekeyserver.auth.service;

import java.util.Optional;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.domain.PasswordAuthenticatedUser;
import com.thekey.stylekeyserver.auth.entity.AuthPasswordEntity;
import com.thekey.stylekeyserver.auth.repository.AuthPasswordRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthPasswordUserService implements UserDetailsService {
    private final AuthPasswordRepository authPasswordJpaRepository;

    public AuthPasswordUserService(AuthPasswordRepository authPasswordJpaRepository) {
        this.authPasswordJpaRepository = authPasswordJpaRepository;
    }

    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthPasswordEntity> authEntityOptional = authPasswordJpaRepository.findById(username);
        if (authEntityOptional.isPresent()) {
            AuthPasswordEntity authEntity = authEntityOptional.get();

            return new PasswordAuthenticatedUser(
                    authEntity.getId(),
                    authEntity.getEncryptedPassword(),
                    AuthorityUtils.NO_AUTHORITIES,
                    authEntity.getUserId()
            );
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}

