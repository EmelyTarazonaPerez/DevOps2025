package com.soporte.app.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SupportProduct{
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String supplierId;
    private String category;
    private LocalDate creationDate;


    public SupportProduct(Long id, String name, String code, Integer quantity, BigDecimal unitPrice, String supplierId, String category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
        this.category = category;
        this.creationDate = LocalDate.now();
    }

    public SupportProduct() {
    }

}
