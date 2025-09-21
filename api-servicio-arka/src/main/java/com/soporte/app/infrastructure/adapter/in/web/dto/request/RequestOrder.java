package com.soporte.app.infrastructure.adapter.in.web.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequestOrder {
    private String direction;
    private Integer idClient;
    private Integer idCard;
}
