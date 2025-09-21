package com.soporte.app.aplication.useCase;

import com.soporte.app.domain.model.SupplierModel;
import com.soporte.app.domain.port.out.SupportSupplierPort;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.BodyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupportSupplierUseCase {

    private final SupportSupplierPort supportSupplierPort;

    public BodyResponse getAllSuppliers() {
        return new BodyResponse(200,
                "Suppliers retrieved successfully",
                supportSupplierPort.getAllSuppliers());
    }
    public BodyResponse createSupplier(SupplierModel supplierModel) {
        SupplierModel create = supportSupplierPort.createSupplier(supplierModel);
        return new BodyResponse(201, "Supplier created successfully", create);
    }
    public BodyResponse updateSupplier(SupplierModel supplierModel) {
        supportSupplierPort.updateSupplier(supplierModel);
        return new BodyResponse(200, "Supplier updated successfully", null);
    }
    public BodyResponse deleteSupplier(int supplierId) {
        supportSupplierPort.deleteSupplier(supplierId);
        return new BodyResponse(200, "Supplier deleted successfully", null);
    }
}
