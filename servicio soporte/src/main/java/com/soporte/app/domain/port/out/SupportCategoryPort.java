package com.soporte.app.domain.port.out;

import com.soporte.app.domain.model.CategoryModel;

import java.util.List;

public interface SupportCategoryPort {

    List<CategoryModel> getAllCategories();
    String createCategory(String category);
    boolean updateCategory(int categoryId, String newCategoryName);
    boolean deleteCategory(int categoryId);
}
