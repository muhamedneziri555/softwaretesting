package com.example.javaPharma.service;


import com.example.javaPharma.pojo.dto.CreateCategoryRequest;
import com.example.javaPharma.pojo.dto.UpdateCategoryRequest;
import com.example.javaPharma.pojo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category saveCategory(CreateCategoryRequest category);

    void deleteCategory(Long id);

    Category updateCategory (Long id, UpdateCategoryRequest category);
}
