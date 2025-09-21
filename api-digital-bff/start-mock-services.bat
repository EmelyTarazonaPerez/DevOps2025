@echo off
REM Script para simular los microservicios en Windows

echo 🚀 Iniciando simulación de microservicios...

REM Verificar si json-server está instalado
where json-server >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ⚠️  json-server no está instalado. Instalando...
    npm install -g json-server
)

REM Crear directorio para servicios mock
if not exist "mock-services" mkdir mock-services

REM User Service Mock Data
(
echo {
echo   "users": [
echo     {
echo       "id": "user123",
echo       "username": "johndoe",
echo       "email": "john@example.com",
echo       "firstName": "John",
echo       "lastName": "Doe",
echo       "avatar": "/avatars/john.png"
echo     },
echo     {
echo       "id": "user456",
echo       "username": "janedoe",
echo       "email": "jane@example.com",
echo       "firstName": "Jane",
echo       "lastName": "Doe",
echo       "avatar": "/avatars/jane.png"
echo     }
echo   ]
echo }
) > mock-services\users.json

REM Product Service Mock Data
(
echo {
echo   "products": [
echo     {
echo       "id": "prod1",
echo       "name": "Smartphone X",
echo       "description": "Latest smartphone with amazing features",
echo       "price": 999.99,
echo       "category": "Electronics",
echo       "imageUrl": "/products/smartphone.jpg",
echo       "stock": 50
echo     },
echo     {
echo       "id": "prod2",
echo       "name": "Laptop Pro",
echo       "description": "Professional laptop for developers",
echo       "price": 1599.99,
echo       "category": "Electronics",
echo       "imageUrl": "/products/laptop.jpg",
echo       "stock": 25
echo     },
echo     {
echo       "id": "prod3",
echo       "name": "Running Shoes",
echo       "description": "Comfortable running shoes",
echo       "price": 129.99,
echo       "category": "Sports",
echo       "imageUrl": "/products/shoes.jpg",
echo       "stock": 100
echo     }
echo   ]
echo }
) > mock-services\products.json

REM Order Service Mock Data
(
echo {
echo   "orders": [
echo     {
echo       "id": "order1",
echo       "userId": "user123",
echo       "productId": "prod1",
echo       "quantity": 1,
echo       "totalAmount": 999.99,
echo       "status": "DELIVERED",
echo       "orderDate": "2025-01-15T10:30:00",
echo       "deliveryAddress": "123 Main St, City"
echo     },
echo     {
echo       "id": "order2",
echo       "userId": "user123",
echo       "productId": "prod3",
echo       "quantity": 2,
echo       "totalAmount": 259.98,
echo       "status": "SHIPPED",
echo       "orderDate": "2025-01-20T14:45:00",
echo       "deliveryAddress": "123 Main St, City"
echo     }
echo   ]
echo }
) > mock-services\orders.json

REM Notification Service Mock Data
(
echo {
echo   "notifications": [
echo     {
echo       "id": "notif1",
echo       "title": "Order Shipped",
echo       "message": "Your order #order2 has been shipped",
echo       "type": "ORDER_UPDATE"
echo     },
echo     {
echo       "id": "notif2",
echo       "title": "New Promotion",
echo       "message": "50%% off on Electronics this week!",
echo       "type": "PROMOTION"
echo     }
echo   ]
echo }
) > mock-services\notifications.json

REM Comment Service Mock Data
(
echo {
echo   "comments": [
echo     {
echo       "id": "comment1",
echo       "productId": "prod1",
echo       "userId": "user123",
echo       "username": "johndoe",
echo       "userAvatar": "/avatars/john.png",
echo       "content": "Amazing smartphone! Great camera quality and battery life. Highly recommended!",
echo       "rating": 5,
echo       "likes": 24,
echo       "isHighlighted": true,
echo       "createdAt": "2025-01-10T09:15:00",
echo       "updatedAt": "2025-01-10T09:15:00"
echo     },
echo     {
echo       "id": "comment2",
echo       "productId": "prod1",
echo       "userId": "user456",
echo       "username": "janedoe",
echo       "userAvatar": "/avatars/jane.png",
echo       "content": "Good phone but a bit expensive for the features provided.",
echo       "rating": 4,
echo       "likes": 12,
echo       "isHighlighted": false,
echo       "createdAt": "2025-01-12T14:30:00",
echo       "updatedAt": "2025-01-12T14:30:00"
echo     },
echo     {
echo       "id": "comment3",
echo       "productId": "prod2",
echo       "userId": "user123",
echo       "username": "johndoe",
echo       "userAvatar": "/avatars/john.png",
echo       "content": "Perfect laptop for development work. Fast performance and excellent keyboard!",
echo       "rating": 5,
echo       "likes": 18,
echo       "isHighlighted": true,
echo       "createdAt": "2025-01-15T16:45:00",
echo       "updatedAt": "2025-01-15T16:45:00"
echo     },
echo     {
echo       "id": "comment4",
echo       "productId": "prod3",
echo       "userId": "user456",
echo       "username": "janedoe",
echo       "userAvatar": "/avatars/jane.png",
echo       "content": "Very comfortable running shoes. Great for long distance running.",
echo       "rating": 4,
echo       "likes": 8,
echo       "isHighlighted": true,
echo       "createdAt": "2025-01-18T11:20:00",
echo       "updatedAt": "2025-01-18T11:20:00"
echo     }
echo   ]
echo }
) > mock-services\comments.json

REM Crear archivo de rutas
(
echo {
echo   "/api/users/*": "/users/$1",
echo   "/api/products/*": "/products/$1",
echo   "/api/orders/*": "/orders/$1",
echo   "/api/notifications/*": "/notifications/$1",
echo   "/api/comments/*": "/comments/$1"
echo }
) > mock-services\routes.json

echo 📂 Archivos mock creados en mock-services\

echo 🎯 Iniciando servicios mock...

REM Iniciar servicios en background
start "User Service" json-server --watch mock-services\users.json --port 8081 --routes mock-services\routes.json
start "Product Service" json-server --watch mock-services\products.json --port 8082 --routes mock-services\routes.json
start "Order Service" json-server --watch mock-services\orders.json --port 8083 --routes mock-services\routes.json
start "Notification Service" json-server --watch mock-services\notifications.json --port 8084 --routes mock-services\routes.json
start "Comment Service" json-server --watch mock-services\comments.json --port 8085 --routes mock-services\routes.json

echo.
echo ✅ Todos los microservicios mock están iniciándose:
echo 👤 User Service: http://localhost:8081/api/users
echo 🛍️  Product Service: http://localhost:8082/api/products
echo 📦 Order Service: http://localhost:8083/api/orders
echo 🔔 Notification Service: http://localhost:8084/api/notifications
echo 💬 Comment Service: http://localhost:8085/api/comments
echo.
echo ⏳ Esperando 15 segundos para que los servicios inicien completamente...
timeout /t 15 /nobreak > nul

echo.
echo 🧪 Prueba los endpoints:
echo curl http://localhost:8081/api/users
echo curl http://localhost:8082/api/products
echo curl http://localhost:8083/api/orders
echo curl http://localhost:8084/api/notifications
echo curl http://localhost:8085/api/comments
echo.
echo Para detener los servicios, cierra las ventanas de json-server o usa Ctrl+C

pause
