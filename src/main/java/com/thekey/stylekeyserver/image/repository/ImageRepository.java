package com.thekey.stylekeyserver.image.repository;

import com.thekey.stylekeyserver.image.domain.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByIsUsed(boolean b);
}
