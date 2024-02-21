package com.thekey.stylekeyserver.test.service;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.ApiException;
import com.thekey.stylekeyserver.test.TestErrorMessage;
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
    public Long createTestResult(TestResultRequest request, String userId) {
        User user = userRepository.findByUserId(userId);
        Map<StylePoint, Integer> stylePointScores = calculateStylePointScore(request);
        TestResult testResult = TestResult.create(user, stylePointScores);

        TestResult savedTestResult = testResultRepository.save(testResult);
        return savedTestResult.getId();
    }

    private Map<StylePoint, Integer> calculateStylePointScore(TestResultRequest request) {
        return request.getAnswerIds().stream()
                .map(answerId -> testAnswerRepository.findById(answerId)
                        .orElseThrow(() -> new ApiException(TestErrorMessage.TEST_ANSWER_NOT_FOUND)))
                .flatMap(testAnswer -> testAnswer.getTestAnswerDetails().stream())
                .collect(Collectors.toMap(
                        TestAnswerDetail::getStylePoint,
                        TestAnswerDetail::getScore,
                        Integer::sum));
    }

    public List<TestResultResponse> getTestResults(String userId) {
        User user = userRepository.findByUserId(userId);
        List<TestResult> testResults = testResultRepository.findAllByUser(user);

        return testResults.stream()
                .map(TestResultResponse::of)
                .toList();
    }

    public TestResultResponse findTestResult(String userId, Long testResultId) {
        TestResult testResult = testResultRepository.findById(testResultId)
                .orElseThrow(() -> new ApiException(TestErrorMessage.TEST_RESULT_NOT_FOUND));
        validateOwner(userId, testResult);
        return TestResultResponse.of(testResult);
    }

    @Transactional
    public void deleteTestResult(Long testResultId, String userId) {
        User user = userRepository.findByUserId(userId);
        testResultRepository.deleteByUserAndId(user, testResultId);
    }

    private void validateOwner(String userId, TestResult testResult) {
        if (!testResult.isOwner(userId)) {
            throw new ApiException(TestErrorMessage.UNAUTHORIZED_TEST_RESULT);
        }
    }
}
