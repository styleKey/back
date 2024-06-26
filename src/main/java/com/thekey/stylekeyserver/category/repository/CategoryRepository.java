package com.thekey.stylekeyserver.category.repository;

import com.thekey.stylekeyserver.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
