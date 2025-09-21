#!/bin/bash

# Script para simular los microservicios usando json-server o alternativas simples
# Este script crea mock servers para probar los BFF

echo "🚀 Iniciando simulación de microservicios..."

# Verifica si json-server está instalado
if ! command -v json-server &> /dev/null; then
    echo "⚠️  json-server no está instalado. Instalando..."
    npm install -g json-server
fi

# Crear datos mock para cada servicio
mkdir -p mock-services

# User Service Mock Data
cat > mock-services/users.json << 'EOF'
{
  "users": [
    {
      "id": "user123",
      "username": "johndoe",
      "email": "john@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "avatar": "/avatars/john.png"
    },
    {
      "id": "user456",
      "username": "janedoe",
      "email": "jane@example.com",
      "firstName": "Jane",
      "lastName": "Doe",
      "avatar": "/avatars/jane.png"
    }
  ]
}
EOF

# Product Service Mock Data
cat > mock-services/products.json << 'EOF'
{
  "products": [
    {
      "id": "prod1",
      "name": "Smartphone X",
      "description": "Latest smartphone with amazing features",
      "price": 999.99,
      "category": "Electronics",
      "imageUrl": "/products/smartphone.jpg",
      "stock": 50
    },
    {
      "id": "prod2",
      "name": "Laptop Pro",
      "description": "Professional laptop for developers",
      "price": 1599.99,
      "category": "Electronics",
      "imageUrl": "/products/laptop.jpg",
      "stock": 25
    },
    {
      "id": "prod3",
      "name": "Running Shoes",
      "description": "Comfortable running shoes",
      "price": 129.99,
      "category": "Sports",
      "imageUrl": "/products/shoes.jpg",
      "stock": 100
    }
  ]
}
EOF

# Order Service Mock Data
cat > mock-services/orders.json << 'EOF'
{
  "orders": [
    {
      "id": "order1",
      "userId": "user123",
      "productId": "prod1",
      "quantity": 1,
      "totalAmount": 999.99,
      "status": "DELIVERED",
      "orderDate": "2025-01-15T10:30:00",
      "deliveryAddress": "123 Main St, City"
    },
    {
      "id": "order2",
      "userId": "user123",
      "productId": "prod3",
      "quantity": 2,
      "totalAmount": 259.98,
      "status": "SHIPPED",
      "orderDate": "2025-01-20T14:45:00",
      "deliveryAddress": "123 Main St, City"
    }
  ]
}
EOF

# Notification Service Mock Data
cat > mock-services/notifications.json << 'EOF'
{
  "notifications": [
    {
      "id": "notif1",
      "title": "Order Shipped",
      "message": "Your order #order2 has been shipped",
      "type": "ORDER_UPDATE"
    },
    {
      "id": "notif2",
      "title": "New Promotion",
      "message": "50% off on Electronics this week!",
      "type": "PROMOTION"
    }
  ]
}
EOF

echo "📂 Archivos mock creados en mock-services/"

# Función para iniciar un servicio mock
start_service() {
    local service_name=$1
    local port=$2
    local data_file=$3
    
    echo "🎯 Iniciando $service_name en puerto $port..."
    json-server --watch "mock-services/$data_file" --port $port --routes "mock-services/routes.json" &
    
    # Guardar PID para poder matar el proceso después
    echo $! > "mock-services/${service_name}.pid"
}

# Crear archivo de rutas personalizadas
cat > mock-services/routes.json << 'EOF'
{
  "/api/users/*": "/users/$1",
  "/api/products/*": "/products/$1",
  "/api/orders/*": "/orders/$1",
  "/api/notifications/*": "/notifications/$1"
}
EOF

# Iniciar todos los servicios mock
start_service "user-service" 8081 "users.json"
start_service "product-service" 8082 "products.json"
start_service "order-service" 8083 "orders.json"
start_service "notification-service" 8084 "notifications.json"

echo ""
echo "✅ Todos los microservicios mock están ejecutándose:"
echo "👤 User Service: http://localhost:8081/api/users"
echo "🛍️  Product Service: http://localhost:8082/api/products"
echo "📦 Order Service: http://localhost:8083/api/orders"
echo "🔔 Notification Service: http://localhost:8084/api/notifications"
echo ""
echo "Para detener todos los servicios, ejecuta: ./stop-mock-services.sh"

# Crear script para detener servicios
cat > stop-mock-services.sh << 'EOF'
#!/bin/bash
echo "🛑 Deteniendo servicios mock..."

for pid_file in mock-services/*.pid; do
    if [ -f "$pid_file" ]; then
        pid=$(cat "$pid_file")
        kill $pid 2>/dev/null
        rm "$pid_file"
        echo "✅ Servicio detenido (PID: $pid)"
    fi
done

echo "🎉 Todos los servicios mock han sido detenidos"
EOF

chmod +x stop-mock-services.sh

echo "⏳ Esperando 5 segundos para que los servicios inicien completamente..."
sleep 5

echo ""
echo "🧪 Prueba los endpoints:"
echo "curl http://localhost:8081/api/users"
echo "curl http://localhost:8082/api/products"
echo "curl http://localhost:8083/api/orders"
echo "curl http://localhost:8084/api/notifications"
