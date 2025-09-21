## 📌 Event Storming - Servicio de Soporte

### 🔹 Eventos de Dominio más relevantes
1. **Producto agregado al catálogo**  
   Un administrador registra un nuevo producto en el sistema.

2. **Producto actualizado**  
   Se modifican los datos de un producto existente (precio, stock, categoría, etc.).

3. **Producto eliminado**  
   Un producto se retira del catálogo y ya no está disponible.

4. **Carrito creado**  
   Se genera un nuevo carrito de compras para un cliente.

5. **Producto agregado al carrito**  
   Un cliente añade un producto a su carrito.

6. **Producto removido del carrito**  
   Un cliente quita un producto de su carrito.

7. **Carrito pagado**  
   El cliente finaliza la compra y el stock se ajusta.

---

## 🏛 Dominios y Subdominios

- **Dominio principal:** Soporte y gestión de ventas  
- **Subdominios:**  
  - Gestión de productos  
  - Gestión de carritos  

---

## 🧩 Entidades

- **Producto** (id, nombre, código, cantidad, precio, proveedor, categoría)  
- **Carrito** (id, estado, idCliente, fechaActualización, detalles)  
- **DetalleCarrito** (id, producto, cantidad, precioUnitario)  

---

## 🎯 Value Objects

1. **PrecioUnitario** → valor monetario que representa el costo de un producto.  
2. **Cantidad** → número de unidades solicitadas o disponibles.  
3. **CódigoProducto** → identificador único del producto en el catálogo.  

---

## 📦 Bounded Context: Gestión de Productos

**Descripción:**  
Este contexto se encarga de la administración del catálogo de productos, incluyendo altas, bajas, modificaciones y consultas.  

**Lenguaje Ubicuo:**  
- "Catálogo" → conjunto de productos disponibles.  
- "Agregar producto" → registrar un nuevo artículo en el catálogo.  
- "Actualizar producto" → modificar los datos de un artículo existente.  
- "Eliminar producto" → retirar un producto del catálogo.  

---

## 📢 Ejemplo de Evento de Dominio

**Nombre:** ProductoAgregadoAlCatalogo  
**Descripción:** Ocurre cuando un nuevo producto es guardado en el catálogo de soporte.  
**Causa:** El administrador crea un nuevo producto mediante el servicio `addProduct`.  
**Consecuencia:**  
- El producto queda disponible para ser consultado y agregado a carritos.  
- Se puede notificar al resto de servicios que el catálogo cambió.

