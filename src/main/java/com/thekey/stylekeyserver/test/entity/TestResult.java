package com.thekey.stylekeyserver.test.entity;

import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.base.BaseTimeEntity;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Users user;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResultDetail> testResultDetails = new ArrayList<>();

    @Builder
    private TestResult(Users user, Map<StylePoint, Integer> stylePointScores) {
        this.user = user;
        this.testResultDetails = stylePointScores.entrySet().stream()
            .map(entry -> TestResultDetail.of(this, entry.getKey(), entry.getValue()))
            .toList();
    }

    public static TestResult create(Users user, Map<StylePoint, Integer> stylePointScores) {
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
}