package com.thekey.stylekeyserver.category.controller;

import com.thekey.stylekeyserver.category.entity.Category;
import com.thekey.stylekeyserver.category.service.CategoryService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category", description = "Category API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One Category", description = "카테고리 정보 단건 조회")
    public ApiResponse<Category> getCategory(@PathVariable Long id) {
        Optional<Category> optional = Optional.ofNullable(categoryService.findById(id));

        return optional
                .map(ApiResponse::ok)
                .orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.CATEGORY_NOT_FOUND.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All Categories", description = "카테고리 정보 전체 조회")
    public ApiResponse<List<Category>> getCategories() {
        return ApiResponse.ok(categoryService.findAll());
    }
}
