// package com.thekey.stylekeyserver.test.dto.request;

// import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
// import com.fasterxml.jackson.databind.annotation.JsonNaming;
// import com.thekey.stylekeyserver.oauth.domain.Users;
// import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
// import com.thekey.stylekeyserver.test.entity.TestResult;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @JsonNaming(SnakeCaseStrategy.class)
// public class TestResultRequest {

//     private Long stylePointId;
//     private Integer score;

//     @Builder
//     private TestResultRequest(Long stylePointId, Integer score) {
//         this.stylePointId = stylePointId;
//         this.score = score;
//     }

//     @Builder
//     public static TestResult toEntity(Users user, StylePoint stylePoint, TestResultRequest request) {
//         return TestResult.builder()
//             .user(user)
//             .stylePoint(stylePoint)
//             .score(request.getScore())
//             .build();
//     }
// }
