package com.thekey.stylekeyserver.test.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.DUPLICATE_TEST_ANSWERS;
import static com.thekey.stylekeyserver.common.exception.ErrorCode.STYLE_POINT_NOT_FOUND;
import static com.thekey.stylekeyserver.common.exception.ErrorCode.TEST_RESULT_NOT_FOUND;
import static com.thekey.stylekeyserver.common.exception.ErrorCode.UNAUTHORIZED_TEST_RESULT;
import static com.thekey.stylekeyserver.common.exception.ErrorCode.USER_NOT_FOUND;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.common.exception.ApiException;
import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import com.thekey.stylekeyserver.stylepoint.repository.StylePointRepository;
import com.thekey.stylekeyserver.test.dto.request.SaveTestResultRequest;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.entity.TestAnswer;
import com.thekey.stylekeyserver.test.entity.TestAnswerDetail;
import com.thekey.stylekeyserver.test.entity.TestResult;
import com.thekey.stylekeyserver.test.repository.TestAnswerRepository;
import com.thekey.stylekeyserver.test.repository.TestResultRepository;
import java.util.HashMap;
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
    private final StylePointRepository stylePointRepository;

    public TestResultResponse createTestResult(TestResultRequest request) {
        List<TestAnswer> testAnswers = testAnswerRepository.findByIdIn(request.getAnswerIds());
        validateTestAnswer(testAnswers);
        Map<StylePoint, Integer> stylePointScores = calculateStylePointScore(testAnswers);
        TestResult testResult = TestResult.createWithoutUser(stylePointScores);
        return TestResultResponse.of(testResult);
    }

    private void validateTestAnswer(List<TestAnswer> testAnswers) throws ApiException {
        Map<Long, Boolean> questionAnswered = new HashMap<>();
        for (TestAnswer answer : testAnswers) {
            Long questionId = answer.getTestQuestion().getId();
            questionAnswered.put(questionId, true);
        }
        if (testAnswers.size() != questionAnswered.size()) {
            throw new ApiException(DUPLICATE_TEST_ANSWERS);
        }
    }

    @Transactional
    public Long saveTestResult(SaveTestResultRequest request, String userId) {
        User findUser = findUser(userId);
        Map<StylePoint, Integer> stylePoints = findStylePoint(request);
        TestResult testResult = TestResult.create(findUser, stylePoints);
        TestResult savedTestResult = testResultRepository.save(testResult);
        return savedTestResult.getId();
    }

    public List<TestResultResponse> getTestResults(String userId) {
        User user = findUser(userId);
        List<TestResult> testResults = testResultRepository.findAllByUser(user);
        return testResults.stream()
            .map(TestResultResponse::of)
            .toList();
    }

    public TestResultResponse findTestResult(String userId, Long testResultId) {
        TestResult testResult = testResultRepository.findById(testResultId)
            .orElseThrow(() -> new ApiException(TEST_RESULT_NOT_FOUND));
        validateOwner(userId, testResult);
        return TestResultResponse.of(testResult);
    }

    @Transactional
    public void deleteTestResult(Long testResultId, String userId) {
        User user = findUser(userId);
        testResultRepository.deleteByUserAndId(user, testResultId);
    }

    private User findUser(String userId) {
        User findUser = userRepository.findByUserId(userId);
        if (findUser == null) {
            throw new ApiException(USER_NOT_FOUND);
        }
        return findUser;
    }

    private void validateOwner(String userId, TestResult testResult) {
        if (!testResult.isOwner(userId)) {
            throw new ApiException(UNAUTHORIZED_TEST_RESULT);
        }
    }

    private Map<StylePoint, Integer> calculateStylePointScore(List<TestAnswer> testAnswers) {
        return testAnswers.stream()
            .flatMap(testAnswer -> testAnswer.getTestAnswerDetails().stream())
            .collect(Collectors.toMap(
                TestAnswerDetail::getStylePoint,
                TestAnswerDetail::getScore,
                Integer::sum));
    }

    private Map<StylePoint, Integer> findStylePoint(SaveTestResultRequest request) {
        Map<StylePoint, Integer> stylePointsMap = new HashMap<>();
        Map<Long, Integer> requestStylePoints = request.getStylePoints();
        for (Map.Entry<Long, Integer> entry : requestStylePoints.entrySet()) {
            StylePoint stylePoint = stylePointRepository.findById(entry.getKey())
                .orElseThrow(() -> new ApiException(STYLE_POINT_NOT_FOUND));
            stylePointsMap.put(stylePoint, entry.getValue());
        }

        return stylePointsMap;
    }
}
