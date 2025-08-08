package com.soporte.app.infrastructure.controller.rest;

import com.soporte.app.aplication.useCase.SupportCategoryUseCase;
import com.soporte.app.infrastructure.controller.dto.BodyResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SupportCategoryController {

    private final SupportCategoryUseCase supportCategoryUseCase;

    // Define endpoints for support categories here
    // Example:
     @GetMapping("/categories")
    public BodyResponse getAllCategories() {
         // Call the use case to get all categories
         return supportCategoryUseCase.getAllCategories();
    }

    @GetMapping("/categories/create")
    public BodyResponse createCategory(String categoryName) {
        // Call the use case to create a new category
        return supportCategoryUseCase.createCategory(categoryName);
    }
    @GetMapping("/categories/update/{id}")
    public BodyResponse updateCategory(Integer id, String categoryName) {
        // Call the use case to update an existing category
        return supportCategoryUseCase.updateCategory(id, categoryName);
    }
    @GetMapping("/categories/delete/{id}")
    public BodyResponse deleteCategory(Integer id) {
        // Call the use case to delete a category
        return supportCategoryUseCase.deleteCategory(id);
    }


}
