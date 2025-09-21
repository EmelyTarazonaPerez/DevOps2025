package com.example.demo.dto.web;

import com.example.demo.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WebProductCommentsDto {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("comments")
    private List<CommentDto> comments;

    public WebProductCommentsDto(String productId, String name, List<CommentDto> comments) {
        this.name = name;
        this.productId = productId;
        this.comments = comments;
    }
    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
