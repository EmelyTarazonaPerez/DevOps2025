package com.soporte.app.infrastructure.repository.mapping;


import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IMapperEntity {

    ProductEntity modelToEntity(SupportProduct product);

    SupportProduct entityToModel(ProductEntity entity);
    List<SupportProduct> entityListToModelList(List<ProductEntity> entityList);
}
