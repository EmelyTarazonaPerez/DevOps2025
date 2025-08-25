# Análisis de Bounded Contexts en Proyecto SOPORTE

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

