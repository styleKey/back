package com.thekey.stylekeyserver.item.repository;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemByCoordinateLook(CoordinateLook coordinateLook);
    boolean existsByCoordinateLookId(Long coordinateLookId);
}
