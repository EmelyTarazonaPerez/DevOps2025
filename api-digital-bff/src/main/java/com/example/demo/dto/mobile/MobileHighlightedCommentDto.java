package com.example.demo.dto.mobile;

import com.example.demo.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MobileHighlightedCommentDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;

    @JsonProperty("comment")
    private CommentDto comment;
    
    @JsonProperty("productName")
    private String productName;
    
    @JsonProperty("productImage")
    private String productImage;
    
    @JsonProperty("isVerifiedPurchase")
    private Boolean isVerifiedPurchase;

    // Constructors
    public MobileHighlightedCommentDto() {}

    public MobileHighlightedCommentDto(CommentDto comment, String productName, String productImage, Boolean isVerifiedPurchase) {
        this.comment = comment;
        this.productName = productName;
        this.productImage = productImage;
        this.isVerifiedPurchase = isVerifiedPurchase;
    }

    // Getters and Setters
    public CommentDto getComment() {
        return comment;
    }

    public void setComment(CommentDto comment) {
        this.comment = comment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Boolean getIsVerifiedPurchase() {
        return isVerifiedPurchase;
    }

    public void setIsVerifiedPurchase(Boolean isVerifiedPurchase) {
        this.isVerifiedPurchase = isVerifiedPurchase;
    }

}
