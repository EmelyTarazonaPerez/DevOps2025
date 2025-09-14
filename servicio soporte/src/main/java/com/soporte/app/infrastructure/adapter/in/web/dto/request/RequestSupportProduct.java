package com.soporte.app.infrastructure.adapter.in.web.dto.request;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
public class RequestSupportProduct {
    private String name;
    private String code;
    private String quantity;
    private BigDecimal unitPrice;
    private String supplierId;

}
