package com.soporte.app.domain.port.out;

import com.soporte.app.domain.model.BillModel;

import java.util.List;
import java.util.Optional;

public interface SupportBillPort {
    public BillModel saveBill(Integer id,BillModel billModel);

    List<BillModel> getAllBills();

    Optional<BillModel> getBillById(Integer id);

    void deleteBill(Integer id);
}
