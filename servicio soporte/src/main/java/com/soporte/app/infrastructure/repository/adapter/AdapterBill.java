package com.soporte.app.infrastructure.repository.adapter;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.domain.port.out.SupportBillPort;
import com.soporte.app.infrastructure.repository.IRepositorySupportBill;
import com.soporte.app.infrastructure.repository.entity.BillEntity;
import com.soporte.app.infrastructure.repository.mapping.IMapperEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AdapterBill implements SupportBillPort {

    private final IRepositorySupportBill repositorySupportBill;
    private final IMapperEntity mapperEntity;

    @Override
    public BillModel saveBill(Integer id, BillModel billModel) {
        BillEntity entity = repositorySupportBill.save(mapperEntity.entityToModel(billModel));
        return mapperEntity.entityToModel(entity);
    }

    @Override
    public List<BillModel> getAllBills() {
        return repositorySupportBill.findAll()
                .stream()
                .map(mapperEntity::entityToModel)
                .toList();

    }

    @Override
    public Optional<BillModel> getBillById(Integer id) {
        return repositorySupportBill.findById(id)
                .map(mapperEntity::entityToModel);
    }

    @Override
    public void deleteBill(Integer id) {
        repositorySupportBill.deleteById(id);
    }
}
