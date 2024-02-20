package com.thekey.stylekeyserver.test.service;

import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.entity.TestAnswerDetail;
import com.thekey.stylekeyserver.test.entity.TestResult;
import com.thekey.stylekeyserver.test.repository.TestAnswerRepository;
import com.thekey.stylekeyserver.test.repository.TestResultRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestResultService {

    private final UserRepository userRepository;
    private final TestResultRepository testResultRepository;
    private final TestAnswerRepository testAnswerRepository;

    @Transactional
    public void createTestResult(TestResultRequest request, String userId) {
        Users user = userRepository.findByEmail(userId).orElseThrow();
        Map<StylePoint, Integer> stylePointScores = calculateStylePointScore(request);
        TestResult testResult = TestResult.create(user, stylePointScores);

        testResultRepository.save(testResult);
    }

    private Map<StylePoint, Integer> calculateStylePointScore(TestResultRequest request) {
        return request.getAnswerIds().stream()
            .map(answerId -> testAnswerRepository.findById(answerId).orElseThrow())
            .flatMap(testAnswer -> testAnswer.getTestAnswerDetails().stream())
            .collect(Collectors.toMap(
                TestAnswerDetail::getStylePoint,
                TestAnswerDetail::getScore,
                Integer::sum));
    }

    public List<TestResultResponse> getTestResults(String userId) {
        Users user = userRepository.findByEmail(userId).orElseThrow();
        List<TestResult> testResults = testResultRepository.findAllByUser(user);

        return testResults.stream()
            .map(TestResultResponse::of)
            .toList();
    }

    @Transactional
    public void deleteTestResult(Long testResultId, String userId) {
        Users user = userRepository.findByEmail(userId).orElseThrow();
        testResultRepository.deleteByUserAndId(user, testResultId);
    }
}
