package com.thekey.stylekeyserver.coordinatelook.repository;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateLookRepository extends JpaRepository<CoordinateLook, Long> {
    List<CoordinateLook> findByStylePointId(Long id);
}
