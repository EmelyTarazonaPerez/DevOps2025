package com.soporte.app.infrastructure.adapter.in.web.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestCard {
    private Integer idCard;
    private Integer idClient;
    private Integer idProduct;
    private Integer quantity;
}
