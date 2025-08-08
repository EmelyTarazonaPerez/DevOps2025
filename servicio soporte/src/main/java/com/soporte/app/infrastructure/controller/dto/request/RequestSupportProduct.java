package com.soporte.app.infrastructure.controller.dto.request;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
public class RequestSupportProduct {
    private String name;
    private String code;
    private String quantity;
    private String unitPrice;
    private String supplierId;

}
