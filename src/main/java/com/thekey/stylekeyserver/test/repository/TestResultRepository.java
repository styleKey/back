package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.test.entity.TestResult;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    List<TestResult> findAllByUser(User user);

    void deleteByUserAndId(User user, Long id);
}