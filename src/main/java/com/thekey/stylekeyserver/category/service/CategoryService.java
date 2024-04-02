package com.thekey.stylekeyserver.category.service;

import com.thekey.stylekeyserver.category.entity.Category;
import java.util.List;

public interface CategoryService {
    Category findById(Long id);

    List<Category> findAll();
}
