package com.soporte.app.aplication.useCase;

import com.soporte.app.domain.model.CategoryModel;
import com.soporte.app.domain.port.out.SupportCategoryPort;
import com.soporte.app.infrastructure.controller.dto.BodyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportCategoryUseCase {

    private final SupportCategoryPort supportCategoryPort;

    public BodyResponse getAllCategories() {
        List<CategoryModel> list = supportCategoryPort.getAllCategories();
        return new BodyResponse(200, "Categories retrieved successfully", list);
    }
    public BodyResponse createCategory(String category) {
        String categoryModel = supportCategoryPort.createCategory(category);
        return new BodyResponse(201, "Category created successfully", categoryModel);
    }
    public BodyResponse updateCategory(Integer id, String categoryName) {
        boolean updated = supportCategoryPort.updateCategory(id, categoryName);
        if (updated) {
            return new BodyResponse(200, "Category updated successfully", null);
        } else {
            return new BodyResponse(404, "Category not found", null);
        }
    }
    public BodyResponse deleteCategory(Integer id) {
        boolean deleted = supportCategoryPort.deleteCategory(id);
        if (deleted) {
            return new BodyResponse(200, "Category deleted successfully", null);
        } else {
            return new BodyResponse(404, "Category not found", null);
        }
    }

}
