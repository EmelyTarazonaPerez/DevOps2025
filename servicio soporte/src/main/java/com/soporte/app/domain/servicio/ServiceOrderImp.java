package com.soporte.app.domain.servicio;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.utils.ConstantsMessages;

import java.util.List;

public class ServiceOrderImp {

    public BillModel createBody(BillModel order, CardModel cardUser) {
        validateCardUser(cardUser);
        float subTotal = 0f;

        for (CardProductModel details : cardUser.getDetails()) {
            validateProductDetails(details);
            SupportProduct product = details.getProduct();
            Integer stockStr = product.getQuantity();
            int newStock = stockStr - details.getCantidad();
            product.setQuantity(newStock);
            float price = product.getUnitPrice() * details.getCantidad();
            subTotal += price;
        }

        return mapBillModel(order, subTotal, cardUser);

    }

    private BillModel mapBillModel(BillModel order, Float subTotal, CardModel cardUser) {
        Float iva = 0.12f * subTotal;
        BillModel bill = new BillModel();
        bill.setDirection(order.getDirection());
        bill.setIdClient(cardUser.getIdClient());
        bill.setIdCard(cardUser.getId());
        bill.setSubtotal(subTotal);
        bill.setIva(iva);
        bill.setTotal(subTotal + iva);
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
