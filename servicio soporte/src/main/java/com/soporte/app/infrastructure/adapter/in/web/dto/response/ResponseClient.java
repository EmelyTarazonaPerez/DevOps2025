package com.soporte.app.infrastructure.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseClient {
    private Integer id;
    private String name;
    private String lastName;
    private String age;
    private String phone;
    private String identificationNumber;
    private String identificationType;
}
