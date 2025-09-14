package com.soporte.app.infrastructure.adapter.in.web.mapping;


import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestSupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperProduct {

    ResponseSupportProduct responseSupportProduct(SupportProduct supportProduct);
    List<ResponseSupportProduct> responseSupportProductList(List<SupportProduct> supportProducts);
    SupportProduct requestToModel(RequestSupportProduct responseClient);
}

