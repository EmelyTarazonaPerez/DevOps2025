package com.arka.cotizador.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private Long id;
    private String name;
    private String code;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String supplierId;
    private String category;
    private Integer stock;
    private LocalDate creationDate;


    public Product (Long id, String name, String code, Integer quantity,
                    BigDecimal unitPrice, String supplierId, String category,
                    Integer stock) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
        this.category = category;
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
}

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
