package com.soporte.app.domain.servicio;

import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.controller.dto.RequestSupportProduct;
import com.soporte.app.infrastructure.controller.dto.ResponseSupportProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ServiceSupportProduct {

    public ResponseSupportProduct getResponseSupportProduct(SupportProduct request) {
        return new ResponseSupportProduct(
                request.getId(),
                request.getName(),
                request.getCode(),
                request.getQuantity(),
                request.getUnitPrice(),
                request.getSupplierId()
        );
    }

    public SupportProduct getSupportProduct(ResponseSupportProduct request) {
        return new SupportProduct(request.getId(),
                request.getName(),
                request.getCode(),
                request.getCantity(),
                request.getUnitPrice(),
                request.getSupplierId()

        );
    }
}
