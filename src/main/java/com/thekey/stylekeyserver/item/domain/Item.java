package com.thekey.stylekeyserver.item.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.category.domain.Category;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_title")
    private String title;

    @Column(name = "item_sales_link")
    private String sales_link;

    @Column(name = "item_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinate_look_id")
    @JsonIgnore
    private CoordinateLook coordinateLook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Item(String title, String sales_link, String image, Brand brand, Category category) {
        this.title = title;
        this.sales_link = sales_link;
        this.image = image;
        this.brand = brand;
        this.category = category;
    }

    public void update(String title, String sales_link, String image, Brand brand, Category category) {
        this.title = title;
        this.sales_link = sales_link;
        this.image = image;
        this.brand = brand;
        this.category = category;
    }

    public void setCoordinateLook(CoordinateLook coordinateLook) {
        this.coordinateLook = coordinateLook;
    }
}
