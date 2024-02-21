package com.thekey.stylekeyserver.test.entity;

import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "test_answer")
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_answer_id")
    private Long id;

    @Column(name = "test_answer_content")
    private String content;

    @Column(name = "test_answer_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    private StylePoint stylePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestion;
}
