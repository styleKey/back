package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.test.entity.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {

}
