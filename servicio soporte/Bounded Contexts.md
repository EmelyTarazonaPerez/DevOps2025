# 🏗️ Análisis de Bounded Contexts en Proyecto SOPORTE

## 📋 Definición de Bounded Context
Un **Bounded Context** es un límite dentro del cual un modelo de dominio tiene un significado preciso. Cada contexto mantiene su propio lenguaje ubicuo, evitando ambigüedad y acoplamiento excesivo.

---

## 🔍 Identificación de Bounded Contexts Actuales

### 🛍️ 1. PRODUCT CATALOG CONTEXT (Catálogo de Productos)

**🎯 Responsabilidad:** Gestión de productos, categorías y proveedores.

**📦 Entidades Principales:**
- `SupportProduct` - Producto con código, precio, stock, proveedor.
- `CategoryModel` - Clasificación de productos.
- `SupplierModel` - Proveedor de productos.

**🔄 Casos de Uso:**
- Registrar y actualizar productos.
- Asignar productos a categorías.
- Consultar inventario y precios.
- Gestionar proveedores.

**📁 Ubicación Actual:**
```txt
domain/model/
├── SupportProduct.java    🛍️ Entidad principal
├── CategoryModel.java     📂 Clasificación
├── SupplierModel.java     🏭 Proveedores

application/useCase/
├── SupportProductUseCase.java   🔄 Lógica de productos
├── SupportCategoryUseCase.java  🔄 Lógica de categorías
├── SupportSupplierUseCase.java  🔄 Lógica de proveedores

infrastructure/repository/
├── IRepositorySupportProduct.java
├── IRepositorySupportCategory.java
├── IRepositorySupportSupplier.java
```

**🔑 Conceptos Únicos:**
- **Product:** Identificado por código, con precio y stock.  
- **Category:** Agrupación lógica de productos.  
- **Supplier:** Entidad externa que provee los productos.  

---

### 🛒 2. ORDER MANAGEMENT CONTEXT (Gestión de Órdenes)

**🎯 Responsabilidad:** Manejo de órdenes y carritos.

**📦 Entidades Principales:**
- `CardModel` - Representa una orden o carrito en proceso.  
- `CardProductModel` - Relación entre una orden y sus productos.  

**🔄 Casos de Uso:**
- Crear una orden.  
- Agregar o quitar productos a una orden.  
- Consultar estado de una orden.  

**📁 Ubicación Actual:**
```txt
domain/model/
├── CardModel.java          🛒 Carrito/Orden
├── CardProductModel.java   📦 Línea de producto en orden

application/useCase/
├── SupportCardUseCase.java   🔄 Lógica de órdenes

servicio/
├── ServiceCardImp.java       ⚙️ Implementación de órdenes
```

**🔑 Conceptos Únicos:**
- **Card:** Representa el proceso de compra.  
- **OrderLine (CardProductModel):** Productos y cantidades de cada orden.  
- **Estado:** Puede variar (pendiente, en proceso, completada).  

---

### 💳 3. BILLING CONTEXT (Facturación)

**🎯 Responsabilidad:** Generación y gestión de facturas.

**📦 Entidades Principales:**
- `BillModel` - Factura con totales, IVA, cliente y estado.  

**🔄 Casos de Uso:**
- Crear facturas a partir de órdenes.  
- Calcular totales y aplicar impuestos.  
- Consultar facturas generadas.  

**📁 Ubicación Actual:**
```txt
domain/model/
├── BillModel.java       💳 Factura

application/useCase/
├── SupportBillUseCase.java   🔄 Lógica de facturación

infrastructure/repository/
├── IRepositorySupportBill.java
```

**🔑 Conceptos Únicos:**
- **Factura (Bill):** Documento de cobro.  
- **Subtotal / IVA / Total:** Valores financieros.  
- **Cliente:** Referencia a un cliente de la orden.  

---

## 🚨 Entidades con Posibles Conflictos de Nombre

### 🔍 1. Product (Producto)
- En **Product Catalog** → representa información comercial (precio, stock, categoría).  
- En **Order Management** → usado en `CardProductModel`, representa un producto en el contexto de un pedido.  

⚠️ **Riesgo:** un mismo Product puede tener significados distintos (catálogo vs pedido).

### 🔍 2. Client (Cliente)
- Aparece en `BillModel` y `CardModel`.  
- En **Facturación** → necesario para emitir la factura.  
- En **Órdenes** → propietario de la orden.  

⚠️ **Riesgo:** mismas referencias con distintos atributos requeridos.

---

## 📊 Evaluación del Estado Actual

**🎯 Estado:** Modelo Único pero Modularizado  

✅ **Aspectos Positivos:**
- Paquetes claros para domain, application, infrastructure.  
- Uso de puertos (`SupportXPort`) que desacoplan la infraestructura.  
- Cada agregado (`Bill`, `Card`, `SupportProduct`) tiene sus propios casos de uso.  

⚠️ **Problemas Potenciales:**
- Todas las entidades viven en `domain/model`, lo cual oculta los límites entre contextos.  
- `SupportProduct` es compartido entre Catálogo y Órdenes, sin un *anti-corruption layer*.  
- Crecimiento futuro puede provocar entidades infladas y acoplamiento cruzado.  

---

## 🔄 Estrategia de División

### 🏗️ Fase 1: Modular Monolith (corto plazo)
Separar bounded contexts en paquetes dentro del monolito:
```txt
domain/
├── billing/
│   └── BillModel.java
├── orders/
│   ├── CardModel.java
│   └── CardProductModel.java
├── catalog/
│   ├── SupportProduct.java
│   ├── CategoryModel.java
│   └── SupplierModel.java
```

### 🚀 Fase 2: Microservicios (mediano plazo)
- **Catalog Service** → Productos, categorías, proveedores.  
- **Order Service** → Órdenes y carritos.  
- **Billing Service** → Facturación.  

**Comunicación mediante eventos:**  
`OrderPlaced`, `InvoiceGenerated`, `ProductPriceChanged`.  
