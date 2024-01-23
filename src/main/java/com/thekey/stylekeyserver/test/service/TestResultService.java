package com.thekey.stylekeyserver.test.service;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;
import com.thekey.stylekeyserver.auth.repository.AuthRepository;
import com.thekey.stylekeyserver.stylepoint.StylePointErrorMessage;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.repository.StylePointRepository;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.entity.TestResult;
import com.thekey.stylekeyserver.test.repository.TestResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestResultService {

    private final TestResultRepository testResultRepository;
    private final AuthRepository authRepository;
    private final StylePointRepository stylePointRepository;

    @Transactional
    public TestResultResponse createTestResult(TestResultRequest request) {

        AuthEntity user = authRepository.findByUserId(request.getUserId()).orElseThrow();

        StylePoint stylePoint = stylePointRepository.findById(request.getStylePointId())
            .orElseThrow(() -> new EntityNotFoundException(
                StylePointErrorMessage.NOT_FOUND_STYLE_POINT.get() + request.getStylePointId()));

        TestResult testResult = TestResultRequest.toEntity(user, stylePoint, request);

        return TestResultResponse.of(testResultRepository.save(testResult));
    }
}
