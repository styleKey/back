package com.thekey.stylekeyserver.test.service;

import com.thekey.stylekeyserver.test.dto.response.TestQuestionResponse;
import com.thekey.stylekeyserver.test.entity.TestQuestion;
import com.thekey.stylekeyserver.test.repository.TestQuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestQuestionService {

    private final TestQuestionRepository testQuestionRepository;

    public List<TestQuestionResponse> getTestQuestions() {
        List<TestQuestion> testQuestions = testQuestionRepository.findAllWithAnswers();
        return testQuestions.stream()
            .map(TestQuestionResponse::of)
            .toList();
    }
}