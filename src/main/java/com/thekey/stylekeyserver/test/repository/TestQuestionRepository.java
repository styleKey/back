package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.test.entity.TestQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {

    @Query("SELECT q FROM TestQuestion q JOIN FETCH q.testAnswers")
    List<TestQuestion> findAllWithAnswers();
}
