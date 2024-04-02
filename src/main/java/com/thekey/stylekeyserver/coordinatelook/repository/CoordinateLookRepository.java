package com.thekey.stylekeyserver.coordinatelook.repository;

import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateLookRepository extends JpaRepository<CoordinateLook, Long> {
    List<CoordinateLook> findCoordinateLookByStylePointId(Long id);
}
