package com.thekey.stylekeyserver.brand.repository;

import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandByStylePoint(StylePoint stylePoint);
}
