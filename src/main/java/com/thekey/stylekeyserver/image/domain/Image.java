package com.thekey.stylekeyserver.image.domain;

import com.thekey.stylekeyserver.brand.domain.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", nullable = false)
    private Type type;

    @Column(name = "image_file_name")
    private String fileName;

    @Column(name = "image_is_used")
    private Boolean isUsed;

    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @OneToOne(mappedBy = "image")
    private Brand brand;

    @Builder
    public Image(String url, Type type, String fileName, Boolean isUsed) {
        this.url = url;
        this.type = type;
        this.fileName = fileName;
        this.isUsed = isUsed;
    }

    public void setUnused() {
        this.isUsed = false;
        this.deleteAt = LocalDateTime.now().plusDays(1);
    }
}