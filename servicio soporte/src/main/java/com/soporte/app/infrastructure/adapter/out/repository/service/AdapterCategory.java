package com.soporte.app.infrastructure.adapter.out.repository.service;

import com.soporte.app.domain.model.CategoryModel;
import com.soporte.app.domain.port.out.SupportCategoryPort;
import com.soporte.app.infrastructure.adapter.out.repository.IRepositorySupportCategory;
import com.soporte.app.infrastructure.adapter.out.repository.entity.CategoryEntity;
import com.soporte.app.infrastructure.adapter.out.repository.mapping.IMapperEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdapterCategory implements SupportCategoryPort {

    private final IRepositorySupportCategory repositorySupportCategory;
    private final IMapperEntity mapperEntity;

    @Override
    public List<CategoryModel> getAllCategories() {
        return repositorySupportCategory.findAll()
                .stream()
                .map(categoryEntity -> new CategoryModel(categoryEntity.getId(), categoryEntity.getName()))
                .toList();
    }

    @Override
    public String createCategory(String categoryModel) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryModel);
        return mapperEntity.entityToCategoryModel(repositorySupportCategory.save(category)).getName();
    }

    @Override
    public boolean updateCategory(int categoryId, String newCategoryName) {
        CategoryEntity categoryEntity = repositorySupportCategory.findById(categoryId)
                .orElse(null);
        if (categoryEntity != null) {
            categoryEntity.setName(newCategoryName);
            repositorySupportCategory.save(categoryEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
    if (repositorySupportCategory.existsById(categoryId)) {
        repositorySupportCategory.deleteById(categoryId);
        return true;
    }
    return false;
    }
}
