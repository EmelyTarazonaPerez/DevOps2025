package com.soporte.app.infrastructure.adapter.in.web.mapping;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapperBill {
    @Mapping(target = "state", constant = "PENDIENTE")
    @Mapping(target = "subtotal", constant = "0.0f")
    @Mapping(target = "iva", constant = "0.0f")
    @Mapping(target = "total", constant = "0.0f")
    BillModel requestToModel(RequestOrder requestOrder);
}
