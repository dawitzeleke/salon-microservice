package com.eaii.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.model.Category;
import com.eaii.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>> getCategoriesBySalonId(@PathVariable Long salonId) {
        Set<Category> categories = categoryService.getCategoriesBySalonId(salonId);
        return ResponseEntity.ok(categories);
    }

    @RequestMapping("/salon/{salonId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long salonId) throws Exception {
        Category category = categoryService.getCategoryById(salonId);
        return ResponseEntity.ok(category);
    }
    
}
