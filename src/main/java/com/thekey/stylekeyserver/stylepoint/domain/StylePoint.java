package com.thekey.stylekeyserver.stylepoint.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "StylePoint")
public class StylePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_point_id")
    private Long id;

    @Column(name = "style_point_title")
    private String title;

    @Column(name = "style_point_description")
    private String description;

    @Column(name = "style_point_image")
    private String image;

    @Builder
    public StylePoint(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public void update(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
