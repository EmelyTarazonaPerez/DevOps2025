package com.soporte.app.utils;

import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class Function {

    public ResponseSupportProduct getResponseSupportProduct(SupportProduct request) {
        return new ResponseSupportProduct(
                request.getId(),
                request.getName(),
                request.getCode(),
                request.getDescription(),
                request.getQuantity(),
                request.getUnitPrice(),
                request.getSupplierId(),
                request.getCategory()
        );
    }

    public SupportProduct getSupportProduct(ResponseSupportProduct request) {
        return new SupportProduct(request.getId(),
                request.getName(),
                request.getCode(),
                request.getQuantity(),
                request.getUnitPrice(),
                request.getSupplierId(),
                request.getCategory()
        );
    }
}
