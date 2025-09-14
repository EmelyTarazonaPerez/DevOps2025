package com.arka.gestorsolicitudes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArcaGestorSolicitudesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArcaGestorSolicitudesApplication.class, args);
        System.out.println("Arca Cotizador - Microservicio iniciado exitosamente");
        System.out.println("WebFlux API disponible en: http://localhost:8080");
        System.out.println("Actuator disponible en: http://localhost:8080/actuator");
    }
}