package com.cinema.app.infrastructure.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestClient {
    private String name;
    private String lastName;
    private String age;
    private String phone;
    private String identificationNumber;
    private String identificationType;
}
