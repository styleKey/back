package com.thekey.stylekeyserver.test.controller;

import com.thekey.stylekeyserver.global.oauth.dto.SessionUser;
import com.thekey.stylekeyserver.test.dto.request.TestResultRequest;
import com.thekey.stylekeyserver.test.dto.response.TestQuestionResponse;
import com.thekey.stylekeyserver.test.dto.response.TestResultResponse;
import com.thekey.stylekeyserver.test.service.TestQuestionService;
import com.thekey.stylekeyserver.test.service.TestResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<TestQuestionResponse>> getTestQuestions() {
        return ResponseEntity.ok(testQuestionService.getTestQuestions());
    }

    @Operation(summary = "Create Test-Result", description = "테스트 결과 생성")
    @PostMapping("/test")
    public ResponseEntity<Void> saveTestResult(@RequestBody TestResultRequest request,
        @AuthenticationPrincipal SessionUser user) {
        testResultService.createTestResult(request, user.getUsername());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Read One Test-Result", description = "테스트 결과 단건 조회")
    @GetMapping("/test-result/{testResultId}")
    public ResponseEntity<TestResultResponse> getTestResults(
        @PathVariable Long testResultId, @AuthenticationPrincipal SessionUser user) {
        TestResultResponse testResult = testResultService.findTestResult(user.getUsername(), testResultId);
        return ResponseEntity.ok(testResult);
    }

    @Operation(summary = "Read All Test-Result", description = "테스트 결과 전체 조회")
    @GetMapping("/test-result/list")
    public ResponseEntity<List<TestResultResponse>> getTestResults(@AuthenticationPrincipal SessionUser user) {
        return ResponseEntity.ok(testResultService.getTestResults(user.getUsername()));
    }

    @Operation(summary = "Delete Test-Result", description = "테스트 결과 삭제")
    @DeleteMapping("/test-result/{testResultId}")
    public ResponseEntity<Void> deleteTestResult(@PathVariable Long testResultId,
        @AuthenticationPrincipal SessionUser user) {
        testResultService.deleteTestResult(testResultId, user.getUsername());
        return ResponseEntity.ok().build();
    }
}
