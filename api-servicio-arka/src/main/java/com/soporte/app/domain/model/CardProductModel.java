package com.soporte.app.domain.model;

public class CardProductModel {
    public Integer cantidad;
    public SupportProduct product;

    public CardProductModel() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public SupportProduct getProduct() {
        return product;
    }

    public void setProduct(SupportProduct product) {
        this.product = product;
    }
}
