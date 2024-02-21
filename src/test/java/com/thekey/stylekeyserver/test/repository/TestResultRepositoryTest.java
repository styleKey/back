// package com.thekey.stylekeyserver.test.repository;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.groups.Tuple.tuple;

// import com.thekey.stylekeyserver.oauth.domain.Users;
// import com.thekey.stylekeyserver.oauth.domain.enums.Role;
// import com.thekey.stylekeyserver.oauth.entity.AuthEntity;
// import com.thekey.stylekeyserver.oauth.repository.UserRepository;
// import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
// import com.thekey.stylekeyserver.stylepoint.repository.StylePointRepository;
// import com.thekey.stylekeyserver.test.entity.TestResult;
// import java.time.OffsetDateTime;
// import java.util.List;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.transaction.annotation.Transactional;

// @ActiveProfiles("test")
// @SpringBootTest
// @Transactional
// class TestResultRepositoryTest {

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     StylePointRepository stylePointRepository;

//     @Autowired
//     TestResultRepository testResultRepository;

//     @DisplayName("특정 유저의 테스트 결과 목록을 불러 올 수 있다.")
//     @Test
//     void findAllByUser() {
//         //given
//         Users user = userRepository.save(Users.builder()
//             .email("test@gmail.com")
//             .name("testUser")
//             // .password(null)
//             .role(Role.USER)
//             .provider("Google")
//             .build());

//         StylePoint stylePoint1 = StylePoint.builder().
//             title("글램")
//             .description("글램내용")
//             .image("글램이미지")
//             .build();

//         StylePoint stylePoint2 = StylePoint.builder().
//             title("모던")
//             .description("모던내용")
//             .image("모던이미지")
//             .build();

//         stylePointRepository.saveAll(List.of(stylePoint1, stylePoint2));

//         TestResult testResult1 = TestResult.builder()
//             .user(user)
//             .stylePoint(stylePoint1)
//             .score(1)
//             .build();

//         TestResult testResult2 = TestResult.builder()
//             .user(user)
//             .stylePoint(stylePoint2)
//             .score(5)
//             .build();
//         testResultRepository.saveAll(List.of(testResult1, testResult2));

//         //when
//         List<TestResult> testResults = testResultRepository.findAllByUser(user);

//         //then
//         assertThat(testResults).hasSize(2)
//             .extracting("user", "score")
//             .containsExactlyInAnyOrder(
//                 tuple(user, 1),
//                 tuple(user, 5)
//             );
//     }

//     @DisplayName("특정 유저의 하나의 테스트 결과를 삭제할 수 있다.")
//     @Test
//     void deleteByUserAndId() {
//         //given
//         Users user = userRepository.save(Users.builder()
//             .email("test@gmail.com")
//             .name("testUser")
//             // .password(null)
//             .role(Role.USER)
//             .provider("Google")
//             .build());

//         StylePoint stylePoint1 = StylePoint.builder().
//             title("글램")
//             .description("글램내용")
//             .image("글램이미지")
//             .build();

//         StylePoint stylePoint2 = StylePoint.builder().
//             title("모던")
//             .description("모던내용")
//             .image("모던이미지")
//             .build();

//         stylePointRepository.saveAll(List.of(stylePoint1, stylePoint2));

//         TestResult testResult1 = TestResult.builder()
//             .user(user)
//             .stylePoint(stylePoint1)
//             .score(1)
//             .build();

//         TestResult testResult2 = TestResult.builder()
//             .user(user)
//             .stylePoint(stylePoint2)
//             .score(5)
//             .build();
//         List<TestResult> savedResults = testResultRepository.saveAll(List.of(testResult1, testResult2));
//         Long testResultId = savedResults.get(0).getId();

//         //when
//         testResultRepository.deleteByUserAndId(user, testResultId);
//         List<TestResult> deleteResults = testResultRepository.findAllByUser(user);

//         //then
//         assertThat(deleteResults).hasSize(1);
//         assertThat(deleteResults)
//             .extracting("score")
//             .containsExactlyInAnyOrder(5);
//     }
// }
