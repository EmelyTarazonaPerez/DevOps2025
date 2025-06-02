package com.cliente.app.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {
    private Integer id;
    private String name;
    private String lastName;
    private String age;
    private String phone;
    private String identificationNumber;
    private String identificationType;

    public Client(Integer id, String name, String lastName, String age, String phone, String identificationNumber, String identificationType) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }
}
