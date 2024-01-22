package com.thekey.stylekeyserver.test.controller;

import com.thekey.stylekeyserver.test.dto.response.TestQuestionResponse;
import com.thekey.stylekeyserver.test.service.TestQuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestQuestionService testQuestionService;

    @GetMapping("/test-question")
    public ResponseEntity<List<TestQuestionResponse>> getTestQuestions() {
        return ResponseEntity.ok(testQuestionService.getTestQuestion());
    }
}
