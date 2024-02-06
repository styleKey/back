package com.thekey.stylekeyserver.test.service;

import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.stylepoint.StylePointErrorMessage;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.repository.StylePointRepository;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestResponse;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.entity.TestResult;
import com.thekey.stylekeyserver.test.repository.TestResultRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestResultService {

    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;
    private final StylePointRepository stylePointRepository;

    @Transactional
    public TestResponse createTestResult(TestResultRequest request, String userId) {
        Users user = userRepository.findByEmail(userId).orElseThrow();

        StylePoint stylePoint = stylePointRepository.findById(request.getStylePointId())
            .orElseThrow(() -> new EntityNotFoundException(
                StylePointErrorMessage.NOT_FOUND_STYLE_POINT.get() + request.getStylePointId()));

        TestResult testResult = TestResultRequest.toEntity(user, stylePoint, request);

        return TestResponse.of(testResultRepository.save(testResult));
    }

    public List<TestResultResponse> getTestResult(String userId) {
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
