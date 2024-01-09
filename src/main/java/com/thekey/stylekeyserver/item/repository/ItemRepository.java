package com.thekey.stylekeyserver.item.repository;

import com.thekey.stylekeyserver.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
