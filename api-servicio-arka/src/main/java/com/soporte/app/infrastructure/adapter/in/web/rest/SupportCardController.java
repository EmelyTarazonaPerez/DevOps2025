package com.soporte.app.infrastructure.adapter.in.web.rest;

import com.soporte.app.aplication.useCase.SupportCardUseCase;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.BodyResponse;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestCard;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@AllArgsConstructor
public class SupportCardController {

    private final SupportCardUseCase supportCardUseCase;
    private final ObjectMapper objectMapper;

    public ResponseEntity<BodyResponse> getAllCards() {
        BodyResponse response = supportCardUseCase.getAllCards();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BodyResponse> createCard(RequestCard requestCard) {
        BodyResponse response = supportCardUseCase.saveCard(requestCard);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<BodyResponse> updateCard(RequestCard requestCard) {
        BodyResponse response = supportCardUseCase.updateCard(requestCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BodyResponse> deleteCard(Integer id) {
        BodyResponse response = supportCardUseCase.deleteCard(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
