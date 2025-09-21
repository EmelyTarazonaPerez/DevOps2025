package com.soporte.app.aplication.useCase;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.port.out.SupportBillPort;
import com.soporte.app.domain.port.out.SupportCardPort;
import com.soporte.app.domain.port.out.SupportProductPort;
import com.soporte.app.domain.servicio.ServiceOrderImp;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.BodyResponse;
import com.soporte.app.utils.ConstantsMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.soporte.app.utils.ConstantsMessages.ERROR_AL_GUARDAR_ORDEN;

@Service
@AllArgsConstructor
public class SupportOrderUseCase {
    private final ServiceOrderImp serviceOrderImp;
    private final SupportCardPort supportCardPort;
    private final SupportBillPort supportBillPort;
    private final SupportProductPort supportProductPort;

    public BodyResponse saveOrder(BillModel orderDetails) {
        try {
            Optional<CardModel> cardUser = supportCardPort.getCardDetails(orderDetails.getIdCard());
            if (cardUser.isPresent()) {
                BillModel billModel = serviceOrderImp.createBody(orderDetails, cardUser.get());
                supportBillPort.saveBill(null, billModel);

                for (CardProductModel detailCart : cardUser.get().getDetails()) {
                    supportProductPort.updateProduct(detailCart.getProduct(), detailCart.getProduct().getId());
                }
                return mapBodyResponse(
                        ConstantsMessages.STATUS_OK,
                        ConstantsMessages.PEDIDO_CREADO,
                        billModel);
            } else {
                return mapBodyResponse(
                        ConstantsMessages.STATUS_NO_FOUND,
                        ConstantsMessages.PEDIDO_NO_ENCONTRADO,
                        null);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_AL_GUARDAR_ORDEN + e.getMessage());
        }
    }

    private BodyResponse mapBodyResponse(Integer status, String message, BillModel billModel) {
        return new BodyResponse( status, message, billModel);
    }

    public BodyResponse getAllBills() {
        try {
            return new BodyResponse(
                    ConstantsMessages.STATUS_OK,
                    ConstantsMessages.PEDIDOS_ENCONTRADOS,
                    supportBillPort.getAllBills());
        } catch (Exception e) {
            return new BodyResponse(
                    ConstantsMessages.STATUS_ERROR,
                    ConstantsMessages.ERROR_AL_OBTENER_PEDIDOS,
                    null);
        }
    }

    public BodyResponse getBillById(Integer id) {
        try {
            Optional<BillModel> billModel = supportBillPort.getBillById(id);
            return billModel.map(
                    model -> new BodyResponse(
                            ConstantsMessages.STATUS_OK,
                            ConstantsMessages.PEDIDOS_ENCONTRADOS,
                            model))
                    .orElseGet(() -> new BodyResponse(
                            ConstantsMessages.STATUS_NO_FOUND,
                            ConstantsMessages.PEDIDO_NO_ENCONTRADO + id,
                            null));
        } catch (Exception e) {
            return new BodyResponse(
                    ConstantsMessages.STATUS_ERROR,
                    ConstantsMessages.ERROR_AL_OBTENER_PEDIDOS,
                    null);
        }
    }

    public BodyResponse deleteBill(Integer id) {
        try {
            Optional<BillModel> billFind = supportBillPort.getBillById(id);
            if (billFind.isPresent()) {
                Integer idBill = billFind.get().getIdCard();
                Optional<CardModel> cardByBill = supportCardPort.getCardDetails(idBill);
                if (cardByBill.isPresent()) {
                    List<CardProductModel> details = cardByBill.get().getDetails();
                    serviceOrderImp.restoreStock(details);
                    cardByBill.get().getDetails().forEach(
                            product -> supportProductPort.updateProduct(
                                    product.getProduct(),
                                    product.getProduct().getId()));
                }
                supportBillPort.deleteBill(id);
                return new BodyResponse(
                        ConstantsMessages.STATUS_OK,
                        ConstantsMessages.PEDIDO_CREADO,
                        null);
            } else {
                return new BodyResponse(
                        ConstantsMessages.STATUS_NO_FOUND,
                        ConstantsMessages.PEDIDO_NO_ENCONTRADO + id,
                        null);
            }
        } catch (Exception e) {
            return new BodyResponse(
                    ConstantsMessages.STATUS_ERROR,
                    ConstantsMessages.ERROR_AL_OBTENER_PEDIDOS,
                    null);
        }
    }

    public BodyResponse updateBill(Integer id, BillModel billModel) {
        try {
            Optional<BillModel> existingBill = supportBillPort.getBillById(id);
            if (existingBill.isPresent()) {
                BillModel updatedBill = supportBillPort.saveBill(id, billModel);
                return new BodyResponse(
                        ConstantsMessages.STATUS_OK,
                        ConstantsMessages.PEDIDO_CREADO,
                        updatedBill);
            } else {
                return new BodyResponse(
                        ConstantsMessages.STATUS_NO_FOUND,
                        ConstantsMessages.PEDIDO_NO_ENCONTRADO + id,
                        null);
            }
        } catch (Exception e) {
            return new BodyResponse(
                    ConstantsMessages.STATUS_ERROR,
                    ConstantsMessages.ERROR_AL_GUARDAR_ORDEN + e.getMessage(),
                    null);
        }
    }

    public BodyResponse getBillByProduct(String nameProduct) {
        try {
            List<BillModel> bills = (List<BillModel>) supportBillPort.getAllBills();

            List<BillModel> filteredBills = serviceOrderImp.getBillsByProduct(bills, nameProduct);
            return new BodyResponse(
                    ConstantsMessages.STATUS_OK,
                    ConstantsMessages.PEDIDOS_ENCONTRADOS,
                    filteredBills);
        } catch (Exception e) {
            return new BodyResponse(
                    ConstantsMessages.STATUS_ERROR,
                    ConstantsMessages.ERROR_AL_OBTENER_PEDIDOS,
                    null);
        }
    }
}