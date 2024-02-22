package com.thekey.stylekeyserver.coordinatelook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coordinate_look")
public class CoordinateLook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinate_look_id")
    private Long id;

    @Column(name = "coordinate_look_title")
    private String title;

    @Column(name = "coordinate_look_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    @JsonIgnore
    private StylePoint stylePoint;

    @OneToMany(mappedBy = "coordinateLook", cascade = CascadeType.ALL, orphanRemoval = true)    // 코디룩 엔티티를 삭제할 때 해당 엔티티에 속한 아이템들도 함께 삭제
    @JsonIgnore
    private List<Item> items = new ArrayList<>();

    @Builder
    public CoordinateLook(String title, String image, StylePoint stylePoint) {
        this.title = title;
        this.image = image;
        this.stylePoint = stylePoint;
    }

    public void update(String title, String image, StylePoint stylePoint) {
        this.title = title;
        this.image = image;
        this.stylePoint = stylePoint;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setCoordinateLook(this);
    }
}
