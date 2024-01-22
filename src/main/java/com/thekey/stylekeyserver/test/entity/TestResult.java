package com.thekey.stylekeyserver.test.entity;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;
import com.thekey.stylekeyserver.base.BaseTimeEntity;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "test_result")
@Getter
public class TestResult extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AuthEntity user;

    @ManyToOne
    @JoinColumn(name = "style_point_id")
    private StylePoint stylePoint;

    private Integer score;
}
