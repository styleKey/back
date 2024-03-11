package com.thekey.stylekeyserver.test.repository;

import com.thekey.stylekeyserver.test.entity.TestAnswer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {

    List<TestAnswer> findByIdIn(@Param("ids") List<Long> ids);
}
