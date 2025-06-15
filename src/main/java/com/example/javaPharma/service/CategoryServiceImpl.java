package com.example.javaPharma.service;

import com.example.javaPharma.pojo.dto.CreateCategoryRequest;
import com.example.javaPharma.pojo.dto.UpdateCategoryRequest;
import com.example.javaPharma.pojo.entity.Category;
import com.example.javaPharma.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(CreateCategoryRequest category) {
        Category cat = new Category();
        cat.setName(category.getName());
        return categoryRepository.save(cat);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryRequest category) {
       Category oldCategory = categoryRepository.findById(id).orElse(null);
        oldCategory.setName(category.getName());

        return categoryRepository.save(oldCategory);
    }
}
