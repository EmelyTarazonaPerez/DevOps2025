# 💬 Extensión BFF con Microservicio de Comentarios

## 🎯 Funcionalidades Implementadas

### 📱 BFF Móvil - Comentarios Destacados
El BFF móvil está optimizado para mostrar **comentarios destacados** que son más relevantes para usuarios en dispositivos móviles:

#### Endpoints Implementados:
1. **GET** `/api/mobile/v1/products/{productId}/highlighted-comment`
   - Obtiene el comentario más destacado de un producto específico
   - Incluye información del producto (nombre, imagen)
   - Indica si es una compra verificada

2. **GET** `/api/mobile/v1/users/{userId}/recommended-comments`
   - Obtiene comentarios destacados de productos recomendados para el usuario
   - Limitado a un número específico para optimizar el rendimiento móvil

3. **POST** `/api/mobile/v1/comments`
   - Permite crear nuevos comentarios desde el cliente móvil

4. **PATCH** `/api/mobile/v1/comments/{commentId}/like`
   - Permite dar "like" a comentarios desde el cliente móvil

### 🌐 BFF Web - Comentarios Completos
El BFF web proporciona acceso **completo a todos los comentarios** con funcionalidades avanzadas para interfaces de escritorio:

#### Endpoints Implementados:
1. **GET** `/api/web/v1/products/{productId}/comments`
   - Obtiene todos los comentarios de un producto con paginación completa
   - Incluye analíticas detalladas (promedio de calificaciones, distribución)
   - Soporte para ordenamiento por diferentes criterios

2. **GET** `/api/web/v1/products/{productId}/comments/rating/{rating}`
   - Filtra comentarios por calificación específica
   - Útil para análisis detallado de opiniones

3. **GET** `/api/web/v1/products/{productId}/comments/analytics`
   - Proporciona estadísticas completas de comentarios
   - Distribución de calificaciones detallada

4. **POST** `/api/web/v1/comments`
   - Crear nuevos comentarios desde el cliente web

5. **PUT** `/api/web/v1/comments/{commentId}`
   - Actualizar comentarios existentes

6. **DELETE** `/api/web/v1/comments/{commentId}`
   - Eliminar comentarios (función administrativa)

## 🏗️ Arquitectura del Microservicio de Comentarios

### Microservicio Comment Service (Puerto 8085)
- **Función**: Gestión completa de comentarios y calificaciones
- **Endpoints Mock Disponibles**:
  - `GET /api/comments` - Listar todos los comentarios
  - `GET /api/comments/{id}` - Obtener comentario específico
  - `GET /api/comments/product/{productId}/highlighted` - Comentario destacado
  - `GET /api/comments/product/{productId}` - Todos los comentarios de un producto
  - `GET /api/comments/product/{productId}/count` - Cantidad de comentarios
  - `GET /api/comments/product/{productId}/average-rating` - Promedio de calificaciones
  - `GET /api/comments/product/{productId}/rating-distribution` - Distribución de ratings
  - `POST /api/comments` - Crear comentario
  - `PUT /api/comments/{id}` - Actualizar comentario
  - `PATCH /api/comments/{id}/like` - Dar like a comentario
  - `DELETE /api/comments/{id}` - Eliminar comentario

## 🔄 Flujo Reactivo Implementado

### BFF Móvil - Comentario Destacado
```java
public Mono<MobileHighlightedCommentDto> getHighlightedComment(String productId) {
    Mono<CommentDto> commentMono = commentService.getHighlightedCommentByProductId(productId);
    Mono<ProductDto> productMono = productService.getProductById(productId);
    
    return Mono.zip(commentMono, productMono)
            .map(tuple -> new MobileHighlightedCommentDto(
                tuple.getT1(), // comentario
                tuple.getT2().getName(), // nombre del producto
                tuple.getT2().getImageUrl(), // imagen del producto
                isVerifiedPurchase // verificación de compra
            ));
}
```

### BFF Web - Comentarios Completos
```java
public Mono<WebProductCommentsDto> getProductComments(String productId, Integer page, Integer size, String sortBy) {
    return Mono.zip(
        productService.getProductById(productId),
        commentService.getAllCommentsByProductId(productId, page, size, sortBy).collectList(),
        commentService.getCommentCountByProductId(productId),
        commentService.getAverageRatingByProductId(productId),
        commentService.getRatingDistributionByProductId(productId)
    ).map(tuple -> new WebProductCommentsDto(/* datos combinados */));
}
```

## 📊 Datos Mock de Comentarios

### Estructura de Comentarios Mock:
```json
{
  "id": "comment1",
  "productId": "prod1",
  "userId": "user123",
  "username": "johndoe",
  "userAvatar": "/avatars/john.png",
  "content": "Amazing smartphone! Great camera quality and battery life.",
  "rating": 5,
  "likes": 24,
  "isHighlighted": true,
  "createdAt": "2025-01-10T09:15:00",
  "updatedAt": "2025-01-10T09:15:00"
}
```

## 🚀 Diferencias Clave entre BFF Móvil y Web

### BFF Móvil (Optimizado para Rendimiento)
- ✅ **Solo comentarios destacados** para minimizar datos transferidos
- ✅ **Agregación con información del producto** en una sola respuesta
- ✅ **Verificación de compra simplificada**
- ✅ **Respuestas optimizadas** para conexiones móviles
- ✅ **Funcionalidades básicas**: crear comentarios y dar likes

### BFF Web (Funcionalidad Completa)
- ✅ **Todos los comentarios** con paginación completa
- ✅ **Analíticas detalladas** de calificaciones y distribución
- ✅ **Filtros avanzados** por calificación
- ✅ **Funcionalidades administrativas** completas (CRUD)
- ✅ **Estadísticas en tiempo real** para administradores
- ✅ **Ordenamiento y búsqueda** avanzada

## 🧪 Ejemplos de Uso

### Móvil - Obtener Comentario Destacado
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/products/prod1/highlighted-comment"
```

**Respuesta Optimizada:**
```json
{
  "comment": {
    "id": "comment1",
    "content": "Amazing smartphone! Great camera quality...",
    "rating": 5,
    "likes": 24,
    "username": "johndoe"
  },
  "productName": "Smartphone X",
  "productImage": "/products/smartphone.jpg",
  "isVerifiedPurchase": true
}
```

### Web - Obtener Todos los Comentarios
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/prod1/comments?page=0&size=20"
```

**Respuesta Completa:**
```json
{
  "productId": "prod1",
  "productName": "Smartphone X",
  "comments": [...],
  "totalComments": 156,
  "averageRating": 4.2,
  "ratingDistribution": {
    "fiveStars": 45,
    "fourStars": 38,
    "threeStars": 25,
    "twoStars": 12,
    "oneStar": 5
  },
  "pagination": {
    "currentPage": 0,
    "totalPages": 8,
    "hasNext": true,
    "hasPrevious": false
  }
}
```

## ⚡ Características Reactivas de Resiliencia

### Circuit Breaker para Comment Service
```properties
resilience4j.circuitbreaker.instances.comment-service.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.comment-service.wait-duration-in-open-state=30s
```

### Fallback para Comentarios Destacados
- Si el servicio de comentarios falla, el BFF móvil devuelve un comentario genérico positivo
- El BFF web maneja graciosamente la falta de comentarios mostrando mensaje informativo
- Timeouts configurados para evitar esperas largas en dispositivos móviles

## 🎯 Conclusión

Esta implementación demuestra cómo los BFF pueden servir diferentes necesidades de clientes:

- **Cliente Móvil**: Obtiene comentarios destacados optimizados para la experiencia móvil
- **Cliente Web**: Accede a comentarios completos con todas las funcionalidades administrativas

Ambos BFF consumen el mismo microservicio de comentarios pero adaptan las respuestas según las necesidades específicas de cada plataforma, manteniendo la eficiencia y usabilidad óptima para cada contexto de uso.
