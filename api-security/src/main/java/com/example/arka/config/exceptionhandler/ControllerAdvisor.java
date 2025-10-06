package com.example.arka.config.exceptionhandler;

import com.example.arka.adapters.driven.jpa.msql.exception.ErrorUserBd;
import com.example.arka.adapters.driving.http.handler.InvalidAutorization;
import com.example.arka.domain.exception.ExceptionInsertUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorUserBd.class)
    public ResponseEntity<ExceptionResponse> handleErrorUserBd(ErrorUserBd exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse
                (String.format( exception.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
                ));
    }

    @ExceptionHandler(ExceptionInsertUser.class)
    public ResponseEntity<ExceptionResponse> handleErrorInsertUser (ExceptionInsertUser exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse
                (String.format( exception.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InvalidAutorization.class)
    public ResponseEntity<ExceptionResponse> handleErrorInvalidAutorization (InvalidAutorization exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse
                (String.format( exception.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.toString(), LocalDateTime.now()
                ));
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
