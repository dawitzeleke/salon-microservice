package com.eaii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.model.Category;
import com.eaii.payload.dto.SalonDto;
import com.eaii.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L); // Replace with actual salon ID from the request or authentication context
        Category savedCategory = categoryService.createCategory(category, salonDto);
        return ResponseEntity.ok(savedCategory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L); // Replace with actual salon ID from the request or authentication context
        categoryService.deleteCategory(salonDto.getId(), id);
        return ResponseEntity.ok("Category deleted");
    }
}
