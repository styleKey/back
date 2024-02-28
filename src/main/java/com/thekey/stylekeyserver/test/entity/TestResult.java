package com.thekey.stylekeyserver.test.entity;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.base.BaseTimeEntity;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "test_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResultDetail> testResultDetails = new ArrayList<>();

    @Builder
    private TestResult(Long id, User user, Map<StylePoint, Integer> stylePointScores) {
        this.id = id;
        this.user = user;
        this.testResultDetails = stylePointScores.entrySet().stream()
                .map(entry -> TestResultDetail.of(this, entry.getKey(), entry.getValue()))
                .toList();
    }

    public static TestResult create(User user, Map<StylePoint, Integer> stylePointScores) {
        return TestResult.builder()
                .user(user)
                .stylePointScores(stylePointScores)
                .build();
    }

    public List<TestResultDetail> calculateTopTwoStylePoint() {
        return this.testResultDetails.stream()
                .sorted(Comparator.comparing(TestResultDetail::getScore).reversed())
                .limit(2)
                .toList();
    }

    public boolean isOwner(String userId) {
        if (userId == null) {
            return false;
        }
        return user.getUserId().equals(userId);
    }
}
