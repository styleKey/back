package com.thekey.stylekeyserver.category.service;

import com.thekey.stylekeyserver.category.CategoryErrorMessage;
import com.thekey.stylekeyserver.category.domain.Category;
import com.thekey.stylekeyserver.category.repository.CategoryRepository;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CategoryErrorMessage.NOT_FOUND_CATEGORY.get() + id));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
