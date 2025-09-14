package com.soporte.app.infrastructure.adapter.in.web.dto.request;

import java.util.List;

public class SolicitudRequest {
    private List<Long> productIds;
    private String observaciones;

    public SolicitudRequest() {}

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
