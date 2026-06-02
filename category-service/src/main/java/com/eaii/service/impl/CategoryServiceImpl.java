package com.eaii.service.impl;

import java.util.List;
import java.util.Set;

import com.eaii.model.Category;
import com.eaii.payload.dto.SalonDto;
import com.eaii.repository.CategoryRepository;
import com.eaii.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, SalonDto salonDto) throws Exception {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDto.getId());
        return categoryRepository.save(newCategory); 
    }

    @Override
    public Category getCategoryById(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new Exception("Category not found with id: " + categoryId);
        }
        return category;
    }

    @Override
    public Set<Category> getCategoriesBySalonId(Long salonId) {
        return categoryRepository.findBySalonId(salonId);
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public void deleteCategory(Long categoryId, Long id) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category.getSalonId() != id){
            throw new Exception("Unauthorized to delete this category");
        }
        categoryRepository.delete(category);

    }
    

}
