package com.thekey.stylekeyserver.brand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thekey.stylekeyserver.image.entity.Image;
import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(name = "brand_title")
    private String title;

    @Column(name = "brand_title_eng")
    private String title_eng;

    @Column(name = "brand_site_url")
    private String site_url;

    @OneToOne
    @JoinColumn(name = "brand_image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    @JsonIgnore
    private StylePoint stylePoint;

    @Builder
    public Brand(String title, String title_eng, String site_url, Image image,
                 StylePoint stylePoint) {
        this.title = title;
        this.title_eng = title_eng;
        this.site_url = site_url;
        this.image = image;
        this.stylePoint = stylePoint;
    }

    public void update(String title, String title_eng, String site_url, StylePoint stylePoint) {
        this.title = title;
        this.title_eng = title_eng;
        this.site_url = site_url;
        this.stylePoint = stylePoint;
    }

    public void setImage(Image newImage) {
        if(this.image != null) {
            this.image.setUnused();
        }
        this.image = newImage;
    }
}