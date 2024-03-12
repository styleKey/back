package com.thekey.stylekeyserver.test.controller;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.AUTHENTICATED_FAIL;

import com.thekey.stylekeyserver.common.dto.CreateResponse;
import com.thekey.stylekeyserver.common.exception.ApiException;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import com.thekey.stylekeyserver.test.dto.request.SaveTestResultRequest;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestQuestionResponse;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.service.TestQuestionService;
import com.thekey.stylekeyserver.test.service.TestResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "Test API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestQuestionService testQuestionService;
    private final TestResultService testResultService;

    @Operation(summary = "Read All Test-Question", description = "테스트 질문지 전체 조회")
    @GetMapping("/test-questions")
    public ApiResponse<List<TestQuestionResponse>> getTestQuestions() {
        return ApiResponse.ok(testQuestionService.getTestQuestions());
    }

    @Operation(summary = "Create Test-Result", description = "테스트 결과 생성")
    @PostMapping("/tests/preview")
    public ApiResponse<TestResultResponse> createTestResult(@RequestBody @Validated TestResultRequest request) {
        return ApiResponse.ok(testResultService.createTestResult(request));
    }

    @Operation(summary = "Save Test-Result", description = "테스트 결과 저장")
    @PostMapping("/tests")
    public ApiResponse<CreateResponse> saveTestResult(
        @RequestBody @Validated SaveTestResultRequest request,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        validateLogin(user);
        Long testResultId = testResultService.saveTestResult(request, user.getUserId());
        return ApiResponse.ok(CreateResponse.of(testResultId));
    }

    @Operation(summary = "Read One Test-Result", description = "테스트 결과 단건 조회")
    @GetMapping("/test-result/{testResultId}")
    public ApiResponse<TestResultResponse> getTestResults(
        @PathVariable Long testResultId, @AuthenticationPrincipal UserPrincipal user
    ) {
        validateLogin(user);
        TestResultResponse testResult = testResultService.findTestResult(user.getUserId(), testResultId);
        return ApiResponse.ok(testResult);
    }

    @Operation(summary = "Read All Test-Result", description = "테스트 결과 전체 조회")
    @GetMapping("/test-result/list")
    public ApiResponse<List<TestResultResponse>> getTestResults(@AuthenticationPrincipal UserPrincipal user) {
        validateLogin(user);
        return ApiResponse.ok(testResultService.getTestResults(user.getUserId()));
    }

    @Operation(summary = "Delete Test-Result", description = "테스트 결과 삭제")
    @DeleteMapping("/test-result/{testResultId}")
    public ApiResponse<Void> deleteTestResult(
        @PathVariable Long testResultId,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        validateLogin(user);
        testResultService.deleteTestResult(testResultId, user.getUserId());
        return ApiResponse.ok();
    }

    private void validateLogin(UserPrincipal user) {
        if (user == null) {
            throw new ApiException(AUTHENTICATED_FAIL);
        }
    }
}