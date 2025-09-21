package com.soporte.app.infrastructure.adapter.in.web.dto.response;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSupportProduct {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String supplierId;
    private String category;

}
