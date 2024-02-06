package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.test.entity.TestResult;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    List<TestResult> findAllByUser(Users user);

    void deleteByUserAndId(Users user, Long id);
}
