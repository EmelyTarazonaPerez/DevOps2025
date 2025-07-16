package com.soporte.app.domain.model;

import java.time.LocalDate;

public class SupportProduct{
    private Integer id;
    private String name;
    private String code;
    private String quantity;
    private Float unitPrice;
    private String supplierId;
    private LocalDate creationDate;

    public SupportProduct(Integer id, String name, String code, String quantity, Float unitPrice, String supplierId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
        this.creationDate = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
