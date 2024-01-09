package com.thekey.stylekeyserver.stylepoint.repository;

import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylePointRepository extends JpaRepository<StylePoint, Long> {
}
