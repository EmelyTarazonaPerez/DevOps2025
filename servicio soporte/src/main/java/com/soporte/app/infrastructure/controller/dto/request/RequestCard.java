package com.soporte.app.infrastructure.controller.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestCard {
    private Integer idCard;
    private Integer idClient;
    private Integer idProduct;
    private Integer quantity;
}
