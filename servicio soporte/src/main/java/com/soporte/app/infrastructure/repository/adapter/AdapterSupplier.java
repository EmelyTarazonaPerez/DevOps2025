package com.soporte.app.infrastructure.repository.adapter;

import com.soporte.app.domain.model.SupplierModel;
import com.soporte.app.domain.port.out.SupportSupplierPort;
import com.soporte.app.infrastructure.repository.IRepositorySupportSupplier;
import com.soporte.app.infrastructure.repository.entity.SupplierEntity;
import com.soporte.app.infrastructure.repository.mapping.IMapperEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdapterSupplier implements SupportSupplierPort {

    private final IRepositorySupportSupplier repositorySupportSupplier;
    private final IMapperEntity mapperEntity;

    @Override
    public List<SupplierModel> getAllSuppliers() {
    List<SupplierEntity> supplierModels = repositorySupportSupplier.findAll();
    return supplierModels.stream()
            .map(mapperEntity::entityToSupplierModel)
            .toList();
    }

    @Override
    public SupplierModel createSupplier(SupplierModel supplierModel) {
        SupplierEntity supplierEntity = mapperEntity.modelToSupplierEntity(supplierModel);
        return mapperEntity.entityToSupplierModel(repositorySupportSupplier.save(supplierEntity));
    }

    @Override
    public boolean updateSupplier(SupplierModel supplierModel) {
        SupplierEntity existingSupplier = repositorySupportSupplier.findById(supplierModel.getId())
                .orElse(null);
    if (existingSupplier != null) {
            existingSupplier.setName(supplierModel.getName());
            existingSupplier.setPhone(supplierModel.getPhone());
            existingSupplier.setEmail(supplierModel.getEmail());
            repositorySupportSupplier.save(existingSupplier);
            return true;
    }
        return false;
    }

    @Override
    public boolean deleteSupplier(int supplierId) {
    if (repositorySupportSupplier.existsById(supplierId)) {
            repositorySupportSupplier.deleteById(supplierId);
            return true;
    }
        return false;
    }
}
