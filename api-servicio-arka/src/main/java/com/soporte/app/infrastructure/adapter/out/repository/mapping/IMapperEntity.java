package com.soporte.app.infrastructure.adapter.out.repository.mapping;


import com.soporte.app.domain.model.*;
import com.soporte.app.infrastructure.adapter.out.repository.entity.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IMapperEntity {
    ProductEntity modelToEntity(SupportProduct product);
    SupportProduct entityToModel(ProductEntity entity);
    List<SupportProduct> entityListToModelList(List<ProductEntity> entityList);

    CardModel entityToModel(CardEntity entity);
    CardEntity entityToModel(CardModel model);

    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    BillEntity entityToModel(BillModel entity);
    BillModel entityToModel(BillEntity entity);

    SupplierModel entityToSupplierModel(SupplierEntity entity);
    SupplierEntity modelToSupplierEntity(SupplierModel model);

    CategoryModel entityToCategoryModel(CategoryEntity entity);
    CategoryEntity modelToCategoryEntity(CategoryModel model);
}
