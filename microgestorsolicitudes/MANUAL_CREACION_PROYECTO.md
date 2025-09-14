# Manual de Creación del Proyecto Arca Cotizador

Este manual te guiará paso a paso para crear el microservicio **Arca Cotizador** desde cero, un proyecto Spring Boot WebFlux para gestión de cotizaciones.

## Tabla de Contenidos

1. [Prerrequisitos](#prerrequisitos)
2. [Configuración del Entorno](#configuración-del-entorno)
3. [Creación del Proyecto Base](#creación-del-proyecto-base)
4. [Configuración de Dependencias](#configuración-de-dependencias)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Implementación de Clases](#implementación-de-clases)
7. [Configuración de Aplicación](#configuración-de-aplicación)
8. [Construcción y Ejecución](#construcción-y-ejecución)
9. [Pruebas de la API](#pruebas-de-la-api)
10. [Resolución de Problemas Comunes](#resolución-de-problemas-comunes)

## Prerrequisitos

### Software Necesario

- **Java 17 o superior** (recomendado Java 21)
- **Gradle 8.5+** (se incluye wrapper en el proyecto)
- **IDE** (IntelliJ IDEA, VS Code, Eclipse)
- **Git** (opcional, para control de versiones)
- **Postman** o **curl** (para pruebas de API)

### Verificación de Instalación

```bash
# Verificar Java
java -version

# Verificar disponibilidad de Gradle (opcional)
gradle --version
```

## Configuración del Entorno

### 1. Crear Directorio del Proyecto

```bash
mkdir arca-cotizador
cd arca-cotizador
```

### 2. Inicializar Proyecto Gradle

```bash
gradle init --type java-application --dsl groovy --project-name arca-cotizador
```

Alternativamente, puedes crear manualmente la estructura de directorios.

## Creación del Proyecto Base

### 1. Estructura de Directorios

Crea la siguiente estructura de directorios:

```
arca-cotizador/
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── arka/
│       │   │           └── cotizador/
│       │   │               ├── ArcaCotizadorApplication.java
│       │   │               ├── config/
│       │   │               │   └── WebFluxConfig.java
│       │   │               ├── controller/
│       │   │               │   └── CotizacionController.java
│       │   │               ├── model/
│       │   │               │   ├── CotizacionRequest.java
│       │   │               │   └── CotizacionResponse.java
│       │   │               └── service/
│       │   │                   └── CotizacionService.java
│       │   └── resources/
│       │       ├── application.yml
│       │       └── application-dev.yml
│       └── test/
│           └── java/
│               └── com/
│                   └── arka/
│                       └── cotizador/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

### 2. Archivos de Configuración Base

#### `settings.gradle`

```gradle
rootProject.name = 'arca-cotizador'
include('app')
```

#### `gradle/wrapper/gradle-wrapper.properties`

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.8-bin.zip
networkTimeout=10000
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

## Configuración de Dependencias

### `app/build.gradle`

```gradle
plugins {
    id 'application'
    id 'org.springframework.boot' version '3.2.1'
    id 'io.spring.dependency-management' version '1.1.4'
    id 'java'
}

group = 'com.arka.cotizador'
version = '1.0-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot WebFlux
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    
    // Spring Boot Actuator para monitoring
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    
    // Validation
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // JSON processing
    implementation 'com.fasterxml.jackson.core:jackson-databind'
    
    // Lombok para reducir código boilerplate
    compileOnly 'org.projectlombok:lombok:1.18.30'
    annotationProcessor 'org.projectlombok:lombok:1.18.30'
    
    // Dependencias de utilidad
    implementation 'com.google.guava:guava:31.1-jre'
    
    // Testing
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'io.projectreactor:reactor-test'
    testImplementation 'org.testcontainers:junit-jupiter:1.17.3'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.1'
}

application {
    mainClass = 'com.arka.cotizador.ArcaCotizadorApplication'
}

springBoot {
    mainClass = 'com.arka.cotizador.ArcaCotizadorApplication'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

## Estructura del Proyecto

### Explicación de Paquetes

- **`config/`**: Configuraciones de Spring (WebFlux, CORS, beans)
- **`controller/`**: Controladores REST para endpoints
- **`model/`**: Modelos de datos (DTOs, entidades)
- **`service/`**: Lógica de negocio
- **`resources/`**: Archivos de configuración de la aplicación

## Implementación de Clases

### 1. Clase Principal - `ArcaCotizadorApplication.java`

```java
package com.arka.cotizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicación principal del microservicio Arca Cotizador
 * 
 * Microservicio reactivo desarrollado con Spring WebFlux
 * para gestionar cotizaciones de productos de manera asíncrona.
 * 
 * @author Arka Team
 * @version 1.0.0
 */
@SpringBootApplication
public class ArcaCotizadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArcaCotizadorApplication.class, args);
        System.out.println("🚀 Arca Cotizador - Microservicio iniciado exitosamente");
        System.out.println("🌐 WebFlux API disponible en: http://localhost:8080");
        System.out.println("📊 Actuator disponible en: http://localhost:8080/actuator");
    }
}
```

### 2. Configuración WebFlux - `config/WebFluxConfig.java`

```java
package com.arka.cotizador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuración de WebFlux para el microservicio
 */
@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
    
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();
    }
}
```

### 3. Modelo de Request - `model/CotizacionRequest.java`

```java
package com.arka.cotizador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionRequest {
    
    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreCliente;
    
    @NotNull(message = "El email del cliente es obligatorio")
    private String emailCliente;
    
    @NotNull(message = "La lista de productos es obligatoria")
    @Size(min = 1, message = "Debe incluir al menos un producto")
    private List<ProductoCotizacion> productos;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaSolicitud;
    
    private String observaciones;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoCotizacion {
        
        @NotNull(message = "El código del producto es obligatorio")
        private String codigo;
        
        @NotNull(message = "El nombre del producto es obligatorio")
        private String nombre;
        
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        private Integer cantidad;
        
        private String categoria;
        private String descripcion;
    }
}
```

### 4. Modelo de Response - `model/CotizacionResponse.java`

```java
package com.arka.cotizador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionResponse {
    
    private String id;
    private String nombreCliente;
    private String emailCliente;
    private List<ProductoCotizado> productos;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCotizacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVencimiento;
    
    private String moneda;
    private String estado;
    private String observaciones;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoCotizado {
        private String codigo;
        private String nombre;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal descuento;
        private BigDecimal subtotal;
        private String categoria;
        private String descripcion;
    }
}
```

### 5. Servicio de Negocio - `service/CotizacionService.java`

```java
package com.arka.cotizador.service;

import com.arka.cotizador.model.CotizacionRequest;
import com.arka.cotizador.model.CotizacionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CotizacionService {
    
    private static final BigDecimal TASA_IMPUESTO = new BigDecimal("0.19");
    private static final int DIAS_VALIDEZ = 30;
    
    public Mono<CotizacionResponse> procesarCotizacion(CotizacionRequest request) {
        log.info("Procesando cotización para cliente: {}", request.getNombreCliente());
        
        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return calcularCotizacion(request);
        })
        .doOnSuccess(response -> log.info("Cotización procesada con ID: {}", response.getId()))
        .doOnError(error -> log.error("Error procesando cotización", error));
    }
    
    private CotizacionResponse calcularCotizacion(CotizacionRequest request) {
        String id = UUID.randomUUID().toString();
        LocalDateTime fechaCotizacion = LocalDateTime.now();
        LocalDateTime fechaVencimiento = fechaCotizacion.plusDays(DIAS_VALIDEZ);
        
        List<CotizacionResponse.ProductoCotizado> productos = request.getProductos().stream()
                .map(this::calcularProducto)
                .collect(Collectors.toList());
        
        BigDecimal subtotal = productos.stream()
                .map(CotizacionResponse.ProductoCotizado::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal impuestos = subtotal.multiply(TASA_IMPUESTO);
        BigDecimal total = subtotal.add(impuestos);
        
        return CotizacionResponse.builder()
                .id(id)
                .nombreCliente(request.getNombreCliente())
                .emailCliente(request.getEmailCliente())
                .productos(productos)
                .subtotal(subtotal)
                .impuestos(impuestos)
                .total(total)
                .fechaCotizacion(fechaCotizacion)
                .fechaVencimiento(fechaVencimiento)
                .moneda("COP")
                .estado("ACTIVA")
                .observaciones(request.getObservaciones())
                .build();
    }
    
    private CotizacionResponse.ProductoCotizado calcularProducto(CotizacionRequest.ProductoCotizacion producto) {
        BigDecimal precioUnitario = calcularPrecioUnitario(producto.getCodigo());
        BigDecimal descuento = BigDecimal.ZERO;
        BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(producto.getCantidad()));
        
        return CotizacionResponse.ProductoCotizado.builder()
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .cantidad(producto.getCantidad())
                .precioUnitario(precioUnitario)
                .descuento(descuento)
                .subtotal(subtotal)
                .categoria(producto.getCategoria())
                .descripcion(producto.getDescripcion())
                .build();
    }
    
    private BigDecimal calcularPrecioUnitario(String codigo) {
        int hash = Math.abs(codigo.hashCode());
        int precio = (hash % 100000) + 10000;
        return new BigDecimal(precio);
    }
}
```

### 6. Controlador REST - `controller/CotizacionController.java`

```java
package com.arka.cotizador.controller;

import com.arka.cotizador.model.CotizacionRequest;
import com.arka.cotizador.model.CotizacionResponse;
import com.arka.cotizador.service.CotizacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/cotizaciones")
@RequiredArgsConstructor
public class CotizacionController {
    
    private final CotizacionService cotizacionService;
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CotizacionResponse> crearCotizacion(@Valid @RequestBody CotizacionRequest request) {
        log.info("Recibida solicitud de cotización para cliente: {}", request.getNombreCliente());
        
        if (request.getFechaSolicitud() == null) {
            request.setFechaSolicitud(LocalDateTime.now());
        }
        
        return cotizacionService.procesarCotizacion(request)
                .doOnNext(response -> log.info("Cotización creada exitosamente con ID: {}", response.getId()));
    }
    
    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.just("Cotizaciones Controller - OK");
    }
    
    @GetMapping("/info")
    public Mono<Object> info() {
        return Mono.just(new Object() {
            public final String service = "Arca Cotizador";
            public final String version = "1.0.0";
            public final LocalDateTime timestamp = LocalDateTime.now();
            public final String description = "Microservicio para gestión de cotizaciones";
        });
    }
}
```

## Configuración de Aplicación

### `src/main/resources/application.yml`

```yaml
# Configuración del Microservicio Arca Cotizador
server:
  port: 8080
  
spring:
  application:
    name: arca-cotizador
  
  # Configuración de WebFlux
  webflux:
    base-path: /api/v1

# Configuración de Actuator
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always

# Configuración de Logging
logging:
  level:
    com.arka.cotizador: INFO
    reactor.netty: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

# Configuración específica del negocio
arca:
  cotizador:
    dias-validez: 30
    tasa-impuesto: 0.19
    margen-comercial: 0.20
    moneda-default: COP
```

### `src/main/resources/application-dev.yml`

```yaml
# Configuración para el entorno de desarrollo
server:
  port: 8080

spring:
  profiles:
    active: dev

# Configuración de desarrollo
logging:
  level:
    com.arka.cotizador: DEBUG
    reactor.netty: DEBUG
    org.springframework.web: DEBUG

# Configuración específica de desarrollo
arca:
  cotizador:
    debug-mode: true
    mock-providers: true
```

## Construcción y Ejecución

### 1. Compilar el Proyecto

```bash
# Desde el directorio raíz del proyecto
./gradlew build
```

### 2. Ejecutar la Aplicación

```bash
# Usando Gradle
./gradlew bootRun

# O usando Java directamente
java -jar app/build/libs/app-1.0-SNAPSHOT.jar
```

### 3. Verificar que la Aplicación Esté Corriendo

```bash
# Health check
curl http://localhost:8080/api/v1/cotizaciones/health

# Actuator health
curl http://localhost:8080/actuator/health

# Info del servicio
curl http://localhost:8080/api/v1/cotizaciones/info
```

## Pruebas de la API

### Ejemplo de Request para Crear Cotización

```bash
curl -X POST http://localhost:8080/api/v1/cotizaciones \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCliente": "Juan Pérez",
    "emailCliente": "juan.perez@email.com",
    "productos": [
      {
        "codigo": "PROD001",
        "nombre": "Laptop Dell",
        "cantidad": 2,
        "categoria": "Tecnología",
        "descripcion": "Laptop Dell Inspiron 15"
      },
      {
        "codigo": "PROD002", 
        "nombre": "Mouse Logitech",
        "cantidad": 3,
        "categoria": "Accesorios",
        "descripcion": "Mouse inalámbrico"
      }
    ],
    "observaciones": "Cotización para empresa"
  }'
```

### Ejemplo de Response

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "nombreCliente": "Juan Pérez",
  "emailCliente": "juan.perez@email.com",
  "productos": [
    {
      "codigo": "PROD001",
      "nombre": "Laptop Dell",
      "cantidad": 2,
      "precioUnitario": 85000.00,
      "descuento": 0.00,
      "subtotal": 170000.00,
      "categoria": "Tecnología",
      "descripcion": "Laptop Dell Inspiron 15"
    }
  ],
  "subtotal": 200000.00,
  "impuestos": 38000.00,
  "total": 238000.00,
  "fechaCotizacion": "2025-07-16 15:30:00",
  "fechaVencimiento": "2025-08-15 15:30:00",
  "moneda": "COP",
  "estado": "ACTIVA",
  "observaciones": "Cotización para empresa"
}
```

## Resolución de Problemas Comunes

### Problema 1: Error de Compilación por BOM

**Error**: `illegal character: '\ufeff'`

**Solución**:
```bash
# En PowerShell (Windows)
$utf8 = New-Object System.Text.UTF8Encoding $false
Get-ChildItem -Path "app/src/main/java" -Recurse -Filter "*.java" | ForEach-Object {
    $content = [System.IO.File]::ReadAllText($_.FullName)
    [System.IO.File]::WriteAllText($_.FullName, $content, $utf8)
}
```

### Problema 2: Incompatibilidad de Versiones Java/Gradle

**Error**: `Unsupported class file major version`

**Solución**:
- Actualizar Gradle a versión 8.5+
- Verificar compatibilidad Java/Gradle
- Usar `sourceCompatibility = '17'` en build.gradle

### Problema 3: Puerto ya en Uso

**Error**: `Port 8080 was already in use`

**Solución**:
```bash
# Cambiar puerto en application.yml
server:
  port: 8081

# O usar variable de entorno
SERVER_PORT=8081 ./gradlew bootRun
```

### Problema 4: Lombok no Funciona

**Solución**:
1. Verificar que el IDE tenga el plugin de Lombok instalado
2. Habilitar annotation processing en el IDE
3. Verificar versión de Lombok en build.gradle

## Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/cotizaciones` | Crear nueva cotización |
| GET | `/api/v1/cotizaciones/health` | Health check del controlador |
| GET | `/api/v1/cotizaciones/info` | Información del servicio |
| GET | `/actuator/health` | Health check de Spring Boot |
| GET | `/actuator/info` | Información de la aplicación |
| GET | `/actuator/metrics` | Métricas de la aplicación |

## Tecnologías Utilizadas

- **Spring Boot 3.2.1**: Framework base
- **Spring WebFlux**: Programación reactiva
- **Lombok**: Reducción de código boilerplate
- **Jackson**: Serialización JSON
- **Jakarta Validation**: Validación de datos
- **Spring Boot Actuator**: Monitoring y gestión
- **Gradle**: Gestión de dependencias y build
- **Java 17+**: Plataforma de desarrollo

## Próximos Pasos

1. **Agregar Base de Datos**: Integrar con MongoDB o PostgreSQL
2. **Implementar Seguridad**: JWT, OAuth2
3. **Agregar Tests**: Unit tests, integration tests
4. **Dockerización**: Crear Dockerfile y docker-compose
5. **Monitoring**: Prometheus, Grafana
6. **Documentación API**: OpenAPI/Swagger
7. **CI/CD**: Pipeline de integración continua

---

**¡Felicitaciones!** Has creado exitosamente el microservicio Arca Cotizador. El proyecto está listo para ser extendido con funcionalidades adicionales según las necesidades del negocio.
