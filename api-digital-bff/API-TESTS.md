# Ejemplos de Pruebas de API - Dual BFF

Este archivo contiene ejemplos de cómo probar los diferentes endpoints de los BFF una vez que estén ejecutándose.

## 🚀 Prerequisitos

1. Ejecutar los microservicios mock:
   ```bash
   # En Windows
   start-mock-services.bat
   
   # En Linux/Mac
   ./start-mock-services.sh
   ```

2. Ejecutar la aplicación BFF:
   ```bash
   ./gradlew bootRun
   ```

## 📱 Pruebas Mobile BFF

### Health Check
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/health" \
     -H "Content-Type: application/json"
```

### Dashboard Móvil Completo
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/dashboard/user123" \
     -H "Content-Type: application/json"
```

### Dashboard Móvil Simplificado
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/dashboard/user123/simple" \
     -H "Content-Type: application/json"
```

### Recomendaciones de Productos
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/recommendations/user123?limit=5" \
     -H "Content-Type: application/json"
```

### Notificaciones del Usuario
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/notifications/user123" \
     -H "Content-Type: application/json"
```

### Marcar Notificación como Leída
```bash
curl -X PATCH "http://localhost:8080/api/mobile/v1/notifications/notif1/read" \
     -H "Content-Type: application/json"
```

### **NUEVO: Comentario Más Destacado de un Producto (BFF Móvil)**
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/products/prod1/highlighted-comment" \
     -H "Content-Type: application/json"
```

### **NUEVO: Comentarios Destacados de Productos Recomendados (BFF Móvil)**
```bash
curl -X GET "http://localhost:8080/api/mobile/v1/users/user123/recommended-comments?limit=3" \
     -H "Content-Type: application/json"
```

### **NUEVO: Crear Comentario desde Móvil**
```bash
curl -X POST "http://localhost:8080/api/mobile/v1/comments" \
     -H "Content-Type: application/json" \
     -d '{
       "productId": "prod1",
       "userId": "user123",
       "content": "Excellent product! Really satisfied with the purchase.",
       "rating": 5
     }'
```

### **NUEVO: Dar Like a Comentario desde Móvil**
```bash
curl -X PATCH "http://localhost:8080/api/mobile/v1/comments/comment1/like" \
     -H "Content-Type: application/json"
```

## 🌐 Pruebas Web BFF

### Health Check
```bash
curl -X GET "http://localhost:8080/api/web/v1/health" \
     -H "Content-Type: application/json"
```

### Dashboard Web Completo
```bash
curl -X GET "http://localhost:8080/api/web/v1/dashboard/user123?page=0&size=10" \
     -H "Content-Type: application/json"
```

### Dashboard Administrativo
```bash
curl -X GET "http://localhost:8080/api/web/v1/dashboard/admin" \
     -H "Content-Type: application/json"
```

### Búsqueda de Productos
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/search?query=laptop&page=0&size=20" \
     -H "Content-Type: application/json"
```

### Productos por Categoría
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/category/Electronics" \
     -H "Content-Type: application/json"
```

### Analíticas de Usuario
```bash
curl -X GET "http://localhost:8080/api/web/v1/analytics/user123" \
     -H "Content-Type: application/json"
```

### **NUEVO: Todos los Comentarios de un Producto (BFF Web)**
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/prod1/comments?page=0&size=20&sortBy=createdAt" \
     -H "Content-Type: application/json"
```

### **NUEVO: Comentarios Filtrados por Calificación (BFF Web)**
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/prod1/comments/rating/5?page=0&size=10" \
     -H "Content-Type: application/json"
```

### **NUEVO: Analíticas de Comentarios de un Producto (BFF Web)**
```bash
curl -X GET "http://localhost:8080/api/web/v1/products/prod1/comments/analytics" \
     -H "Content-Type: application/json"
```

### **NUEVO: Crear Comentario desde Web**
```bash
curl -X POST "http://localhost:8080/api/web/v1/comments" \
     -H "Content-Type: application/json" \
     -d '{
       "productId": "prod2",
       "userId": "user456",
       "content": "Outstanding laptop performance! Perfect for development work.",
       "rating": 5
     }'
```

### **NUEVO: Actualizar Comentario desde Web**
```bash
curl -X PUT "http://localhost:8080/api/web/v1/comments/comment1" \
     -H "Content-Type: application/json" \
     -d '{
       "content": "Updated: Amazing smartphone with excellent features!",
       "rating": 5
     }'
```

### **NUEVO: Eliminar Comentario desde Web (Función Admin)**
```bash
curl -X DELETE "http://localhost:8080/api/web/v1/comments/comment1" \
     -H "Content-Type: application/json"
```

## 🔍 Pruebas de Microservicios Directas

### User Service
```bash
# Obtener todos los usuarios
curl -X GET "http://localhost:8081/api/users" \
     -H "Content-Type: application/json"

# Obtener usuario específico
curl -X GET "http://localhost:8081/api/users/user123" \
     -H "Content-Type: application/json"
```

### Product Service
```bash
# Obtener todos los productos
curl -X GET "http://localhost:8082/api/products" \
     -H "Content-Type: application/json"

# Obtener producto específico
curl -X GET "http://localhost:8082/api/products/prod1" \
     -H "Content-Type: application/json"
```

### Order Service
```bash
# Obtener todas las órdenes
curl -X GET "http://localhost:8083/api/orders" \
     -H "Content-Type: application/json"

# Obtener orden específica
curl -X GET "http://localhost:8083/api/orders/order1" \
     -H "Content-Type: application/json"
```

### Notification Service
```bash
# Obtener todas las notificaciones
curl -X GET "http://localhost:8084/api/notifications" \
     -H "Content-Type: application/json"

# Obtener notificación específica
curl -X GET "http://localhost:8084/api/notifications/notif1" \
     -H "Content-Type: application/json"
```

### **NUEVO: Comment Service**
```bash
# Obtener todos los comentarios
curl -X GET "http://localhost:8085/api/comments" \
     -H "Content-Type: application/json"

# Obtener comentario específico
curl -X GET "http://localhost:8085/api/comments/comment1" \
     -H "Content-Type: application/json"

# Obtener comentarios de un producto específico
curl -X GET "http://localhost:8085/api/comments?productId=prod1" \
     -H "Content-Type: application/json"

# Obtener comentarios destacados (simulado con filtro)
curl -X GET "http://localhost:8085/api/comments?isHighlighted=true" \
     -H "Content-Type: application/json"
```

## 📊 Monitoreo y Health Checks

### Actuator Health
```bash
curl -X GET "http://localhost:8080/actuator/health" \
     -H "Content-Type: application/json"
```

### Actuator Metrics
```bash
curl -X GET "http://localhost:8080/actuator/metrics" \
     -H "Content-Type: application/json"
```

### Actuator Info
```bash
curl -X GET "http://localhost:8080/actuator/info" \
     -H "Content-Type: application/json"
```

## 🧪 Pruebas con PowerShell (Windows)

### Ejemplo de Dashboard Móvil
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/mobile/v1/dashboard/user123" -Method GET -ContentType "application/json"
```

### Ejemplo de Dashboard Web
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/web/v1/dashboard/user123?page=0&size=10" -Method GET -ContentType "application/json"
```

## 🔬 Pruebas de Resiliencia

### Simular Fallo de Microservicio
Para probar la resiliencia, puedes detener uno de los servicios mock y observar cómo el BFF maneja el fallo con fallbacks:

1. Detén el User Service (puerto 8081)
2. Realiza una petición al dashboard
3. Observa la respuesta con datos de fallback

### Ejemplo de Respuesta con Fallback
Cuando el User Service no está disponible, el BFF devuelve un usuario por defecto:
```json
{
  "user": {
    "id": "user123",
    "username": "unknown",
    "email": "unknown@email.com",
    "firstName": "Unknown",
    "lastName": "User",
    "avatar": "/default-avatar.png"
  }
}
```

## 📈 Métricas de Rendimiento

### Comparación Mobile vs Web BFF
- **Mobile BFF**: Optimizado para payloads pequeños y respuestas rápidas
- **Web BFF**: Incluye datos adicionales como analíticas y paginación

### Timeouts Configurados
- **Connection Timeout**: 5 segundos
- **Response Timeout**: 5 segundos
- **Retry Policy**: 3 intentos con backoff exponencial

## 🐛 Debugging

### Logs Detallados
Los logs están configurados en nivel DEBUG para `com.example.demo` y `reactor`, lo que permite ver el flujo reactivo completo.

### Verificar Conectividad
```bash
# Verificar que todos los servicios estén ejecutándose
curl http://localhost:8080/api/mobile/v1/health
curl http://localhost:8080/api/web/v1/health
curl http://localhost:8081/api/users
curl http://localhost:8082/api/products
curl http://localhost:8083/api/orders
curl http://localhost:8084/api/notifications
```
