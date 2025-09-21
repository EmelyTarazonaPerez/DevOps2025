package com.soporte.app.domain.servicio;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.utils.ConstantsMessages;

import java.math.BigDecimal;
import java.util.List;

public class ServiceOrderImp {

    public BillModel createBody(BillModel order, CardModel cardUser) {
        validateCardUser(cardUser);
        BigDecimal subTotal = BigDecimal.ZERO;

        for (CardProductModel details : cardUser.getDetails()) {
            validateProductDetails(details);
            SupportProduct product = details.getProduct();
            Integer stockStr = product.getQuantity();
            int newStock = stockStr - details.getCantidad();
            product.setQuantity(newStock);
            BigDecimal price = product.getUnitPrice().multiply(BigDecimal.valueOf(details.getCantidad()));
            subTotal = subTotal.add(price);
        }

        return mapBillModel(order, subTotal, cardUser);

    }

    private BillModel mapBillModel(BillModel order, BigDecimal subTotal, CardModel cardUser) {
        BigDecimal iva = subTotal.multiply(BigDecimal.valueOf(0.12));
        BillModel bill = new BillModel();
        bill.setDirection(order.getDirection());
        bill.setIdClient(cardUser.getIdClient());
        bill.setIdCard(cardUser.getId());
        bill.setSubtotal(subTotal);
        bill.setIva(iva);
        bill.setTotal(subTotal.add(iva));
        return bill;
    }

    private void validateCardUser(CardModel cardUser) {
        if (cardUser == null || cardUser.getDetails() == null || cardUser.getDetails().isEmpty()) {
            throw new IllegalArgumentException(ConstantsMessages.CART_EMPTY);
        }
    }

    private void validateProductDetails(CardProductModel detail) {
        if (detail.getCantidad() == null || detail.getCantidad() <= 0) {
            throw new IllegalArgumentException(ConstantsMessages.ERROR_CANTIDAD_PRODUCTO);
        }

        SupportProduct product = detail.getProduct();
        if (product == null) {
            throw new IllegalArgumentException(ConstantsMessages.PRODUCTO_NO_DEFINIDO);
        }

        Integer stockStr = product.getQuantity();
        if (stockStr == null || stockStr <= 0) {
            throw new IllegalArgumentException(ConstantsMessages.STOCK_NO_DISPONIBLE);
        }

        int stockDisposable = stockStr;
        if (stockDisposable < detail.getCantidad()) {
            throw new IllegalArgumentException(ConstantsMessages.STOCK_INSUFICIENTE + product.getName() + ".");
        }
    }

    public void restoreStock(List<CardProductModel> products) {
        for (CardProductModel detail : products) {
            SupportProduct product = detail.getProduct();
            Integer stockStr = product.getQuantity();
            int newStock = stockStr + detail.getCantidad();
            product.setQuantity(newStock);
        }
    }

    public List<BillModel> getBillsByProduct(List<BillModel> bills, String productId) {
        return null;
    }

}
