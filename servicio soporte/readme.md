## 🧩 Event Storming - Carrito y Catálogo

### Eventos de Dominio Relevantes

1. **Producto agregado al catálogo**  
   _Cuando un nuevo producto se registra en el sistema._

2. **Producto actualizado**  
   _Cuando cambian los datos de un producto (precio, descripción, etc.)._

3. **Stock reducido por venta**  
   _Cuando se confirma una compra y el stock del producto disminuye._

4. **Carrito creado**  
   _Cuando un cliente inicia un nuevo carrito de compras._

5. **Producto agregado al carrito**  
   _Cuando un cliente añade un producto al carrito._

6. **Producto removido del carrito**  
   _Cuando un cliente quita un producto del carrito._

7. **Carrito pagado**  
   _Cuando se realiza el pago y el carrito se cierra como completado._

8. **Carrito marcado como abandonado**  
   _Cuando pasa cierto tiempo sin actividad en el carrito y se marca como inactivo._

### 📢 Ejemplo de Evento de Dominio

**Nombre:** CarritoMarcadoComoAbandonado  
**Descripción:** Se dispara cuando un carrito de compras permanece inactivo más allá del tiempo configurado en el sistema.  
**Causa:** Falta de interacción del cliente (no agrega ni remueve productos ni avanza al pago).  
**Consecuencia:**  
- El carrito se marca con estado `"ABANDONADO"`.  
- Se podría notificar al cliente para recuperar la compra.  
