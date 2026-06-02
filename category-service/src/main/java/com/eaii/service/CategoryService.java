package com.eaii.service;

import java.util.List;
import java.util.Set;

import com.eaii.model.Category;
import com.eaii.payload.dto.SalonDto;

public interface CategoryService {
    Category createCategory(Category category, SalonDto salonDto) throws Exception;

    Category getCategoryById(Long categoryId) throws Exception;

    Set<Category> getCategoriesBySalonId(Long salonId);

    List<Category> getAllCategories();

    Category updateCategory(Long categoryId, Category category) throws Exception;

    void deleteCategory(Long categoryId, Long id) throws Exception;
}