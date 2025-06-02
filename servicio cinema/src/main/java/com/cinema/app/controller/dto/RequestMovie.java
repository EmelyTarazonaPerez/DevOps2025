package com.cinema.app.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestMovie {
    private String title;
    private String description;
    private Integer idGender;
    private Integer idManager;
    private List<Integer> idActors;
}
