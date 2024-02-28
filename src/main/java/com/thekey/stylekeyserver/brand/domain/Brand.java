package com.thekey.stylekeyserver.brand.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_title")
    private String title;

    @Column(name = "brand_title_eng")
    private String title_eng;

    @Column(name = "brand_description")
    private String description;

    @Column(name = "brand_site_url")
    private String site_url;

    @Column(name = "brand_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    @JsonIgnore
    private StylePoint stylePoint;

    @Builder
    public Brand(String title, String title_eng, String description, String site_url, String image,
                 StylePoint stylePoint) {
        this.title = title;
        this.title_eng = title_eng;
        this.description = description;
        this.site_url = site_url;
        this.image = image;
        this.stylePoint = stylePoint;
    }

    public void update(String title, String title_eng, String description, String site_url, String image,
                       StylePoint stylePoint) {
        this.title = title;
        this.title_eng = title_eng;
        this.description = description;
        this.site_url = site_url;
        this.image = image;
        this.stylePoint = stylePoint;
    }
}
