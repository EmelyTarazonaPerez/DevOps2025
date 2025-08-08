package com.soporte.app.infrastructure.controller.dto.response;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSupportProduct {
    private Integer id;
    private String name;
    private String code;
    private Integer quantity;
    private Float unitPrice;
    private String supplierId;
    private String category;

}
