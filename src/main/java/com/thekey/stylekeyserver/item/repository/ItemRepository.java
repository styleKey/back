package com.thekey.stylekeyserver.item.repository;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.domain.Item;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findItemByCoordinateLook(CoordinateLook coordinateLook, Pageable pageable);
    List<Item> findItemByCoordinateLook(CoordinateLook coordinateLook);
    boolean existsByCoordinateLookId(Long coordinateLookId);
}
