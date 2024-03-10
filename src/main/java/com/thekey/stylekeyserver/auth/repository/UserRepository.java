package com.thekey.stylekeyserver.auth.repository;

import com.thekey.stylekeyserver.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
}
