package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.test.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {

}
