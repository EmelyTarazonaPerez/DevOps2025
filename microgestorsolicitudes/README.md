# Arca Cotizador - Microservicio de Cotizaciones

Microservicio desarrollado con Spring Boot WebFlux para gestionar cotizaciones de productos.

## Tecnologías

- Java 21
- Spring Boot 3.2.1
- Spring WebFlux (Reactive Programming)
- Gradle 8.x

## Estructura del Proyecto

```
src/
 main/
    java/
       com/arka/cotizador/
           ArcaCotizadorApplication.java
           controller/
           service/
           model/
           config/
    resources/
        application.yml
        application-dev.yml
 test/
     java/
         com/arka/cotizador/
```

## Funcionalidades

- Recibir requests de cotización
- Procesar cotizaciones de forma reactiva
- Devolver cotizaciones calculadas
- API REST reactiva con WebFlux

## Ejecutar

```bash
./gradlew bootRun
```

## Testing

```bash
./gradlew test
```

El servicio estará disponible en: http://localhost:8080
