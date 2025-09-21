# Backend for Frontend (BFF) - Dual Client Architecture

Este proyecto implementa dos Backend for Frontend (BFF) con programación reactiva para servir a un cliente móvil y un cliente web, cada uno consumiendo al menos tres microservicios.

## 🏗️ Arquitectura

### BFF para Cliente Móvil
- **Endpoint base**: `/api/mobile/v1`
- **Optimizado para**: Aplicaciones móviles con conexiones limitadas
- **Características**:
  - Respuestas compactas y optimizadas
  - Versión simplificada para conexiones lentas
  - Agregación de datos específicos para móvil

### BFF para Cliente Web  
- **Endpoint base**: `/api/web/v1`
- **Optimizado para**: Aplicaciones web con pantallas grandes
- **Características**:
  - Respuestas detalladas con analíticas
  - Paginación completa
  - Dashboard administrativo

## 🔧 Microservicios Consumidos

### 1. User Service (Puerto 8081)
- **Función**: Gestión de usuarios
- **Endpoints**:
  - `GET /api/users/{id}` - Obtener usuario por ID
  - `GET /api/users` - Listar todos los usuarios
  - `POST /api/users` - Crear usuario
  - `PUT /api/users/{id}` - Actualizar usuario

### 2. Product Service (Puerto 8082)
- **Función**: Catálogo de productos y recomendaciones
- **Endpoints**:
  - `GET /api/products/{id}` - Obtener producto por ID
  - `GET /api/products` - Listar productos
  - `GET /api/products/category/{category}` - Productos por categoría
  - `GET /api/products/recommendations/{userId}` - Recomendaciones personalizadas
  - `GET /api/products/search` - Búsqueda de productos

### 3. Order Service (Puerto 8083)
- **Función**: Gestión de pedidos
- **Endpoints**:
  - `GET /api/orders/{id}` - Obtener pedido por ID
  - `GET /api/orders/user/{userId}` - Pedidos de un usuario
  - `GET /api/orders/user/{userId}/recent` - Pedidos recientes
  - `POST /api/orders` - Crear pedido
  - `PATCH /api/orders/{id}/status` - Actualizar estado de pedido

### 4. Notification Service (Puerto 8084)
- **Función**: Sistema de notificaciones
- **Endpoints**:
  - `GET /api/notifications/user/{userId}` - Notificaciones de usuario
  - `GET /api/notifications/user/{userId}/unread` - Notificaciones no leídas
  - `POST /api/notifications` - Crear notificación
  - `PATCH /api/notifications/{id}/read` - Marcar como leída

## 📱 API Endpoints - Mobile BFF

### Dashboard Móvil
```http
GET /api/mobile/v1/dashboard/{userId}
```
Respuesta optimizada con datos esenciales para móvil

### Dashboard Simplificado
```http
GET /api/mobile/v1/dashboard/{userId}/simple
```
Versión reducida para conexiones lentas

### Recomendaciones
```http
GET /api/mobile/v1/recommendations/{userId}?limit=10
```

### Notificaciones
```http
GET /api/mobile/v1/notifications/{userId}
PATCH /api/mobile/v1/notifications/{notificationId}/read
```

## 🌐 API Endpoints - Web BFF

### Dashboard Web
```http
GET /api/web/v1/dashboard/{userId}?page=0&size=20
```
Dashboard completo con paginación y analíticas

### Dashboard Administrativo
```http
GET /api/web/v1/dashboard/admin
```

### Búsqueda de Productos
```http
GET /api/web/v1/products/search?query=laptop&page=0&size=20
GET /api/web/v1/products/category/{category}
```

### Analíticas
```http
GET /api/web/v1/analytics/{userId}
```

## ⚡ Características Reactivas

### Programación Reactiva con WebFlux
- **Mono**: Para respuestas únicas
- **Flux**: Para streams de datos
- **WebClient**: Cliente HTTP reactivo para consumir microservicios

### Patrones Implementados
1. **Timeout Management**: Timeouts configurables por servicio
2. **Retry Logic**: Reintentos automáticos con backoff exponencial
3. **Circuit Breaker**: Protección contra fallos en cascada
4. **Fallback Responses**: Respuestas por defecto cuando los servicios fallan
5. **Parallel Aggregation**: Consumo paralelo de múltiples microservicios

### Ejemplo de Agregación Reactiva
```java
public Mono<MobileDashboardDto> getMobileDashboard(String userId) {
    Mono<UserDto> userMono = userService.getUserById(userId);
    Mono<List<OrderDto>> ordersMono = orderService.getRecentOrdersByUserId(userId, 5).collectList();
    Mono<List<ProductDto>> productsMono = productService.getRecommendedProducts(userId).take(10).collectList();
    Mono<List<NotificationDto>> notificationsMono = notificationService.getUnreadNotificationsByUserId(userId).take(5).collectList();

    return Mono.zip(userMono, ordersMono, productsMono, notificationsMono)
            .map(tuple -> new MobileDashboardDto(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()));
}
```

## 🚀 Ejecución

### Requisitos
- Java 17+
- Gradle 7+

### Comando de Ejecución
```bash
./gradlew bootRun
```

### URLs de Prueba
- **Mobile BFF Health**: http://localhost:8080/api/mobile/v1/health
- **Web BFF Health**: http://localhost:8080/api/web/v1/health
- **Actuator Health**: http://localhost:8080/actuator/health

### Ejemplo de Dashboard Móvil
```bash
curl http://localhost:8080/api/mobile/v1/dashboard/user123
```

### Ejemplo de Dashboard Web
```bash
curl http://localhost:8080/api/web/v1/dashboard/user123?page=0&size=20
```

## 🔧 Configuración

### Circuit Breaker
```properties
resilience4j.circuitbreaker.instances.user-service.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.user-service.wait-duration-in-open-state=30s
```

### Timeouts
```properties
webclient.connection-timeout=5000
webclient.response-timeout=5000
```

## 📊 Monitoreo

### Health Checks
Cada BFF incluye endpoints de health check para monitoreo

### Actuator
Métricas y health checks disponibles en `/actuator`

## 🛡️ Resiliencia

### Estrategias Implementadas
1. **Timeouts**: Evitar esperas indefinidas
2. **Retries**: Reintentos automáticos con backoff
3. **Circuit Breakers**: Protección contra fallos en cascada
4. **Fallbacks**: Respuestas degradadas cuando los servicios fallan
5. **Bulkhead**: Aislamiento de recursos entre servicios

Esta arquitectura BFF dual proporciona una solución robusta y escalable para servir diferentes tipos de clientes con sus necesidades específicas, mientras mantiene la resiliencia y el rendimiento mediante programación reactiva.
