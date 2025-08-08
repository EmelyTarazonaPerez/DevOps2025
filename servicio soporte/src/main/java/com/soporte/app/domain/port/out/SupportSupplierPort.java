package com.soporte.app.domain.port.out;

import com.soporte.app.domain.model.SupplierModel;

import java.util.List;

public interface SupportSupplierPort {
    List<SupplierModel> getAllSuppliers();
    SupplierModel createSupplier(SupplierModel supplierModel);
    boolean updateSupplier(SupplierModel supplierModel);
    boolean deleteSupplier(int supplierId);
}
