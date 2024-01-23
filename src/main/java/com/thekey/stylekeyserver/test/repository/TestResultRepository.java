package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.test.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
