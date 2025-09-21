package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDto {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("imageUrl")
    private String imageUrl;
    
    @JsonProperty("quantity")
    private Integer quantity;

    // Constructors
    public ProductDto() {}

    public ProductDto(String id, String name, String description, BigDecimal price, String category, String imageUrl, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.quantity = stock;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return unitPrice;
    }

    public void setPrice(BigDecimal price) {
        this.unitPrice = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStock() {
        return quantity;
    }

    public void setStock(Integer stock) {
        this.quantity = stock;
    }
}
