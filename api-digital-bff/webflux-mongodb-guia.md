# WebFlux y MongoDB Reactivo

Esta guía explica cómo construir un microservicio usando **Spring WebFlux** con **MongoDB reactivo**, utilizando `Mono`, `Flux`, y buenas prácticas.

---

## 🚀 ¿Qué es WebFlux?

- **Spring WebFlux** es un módulo de Spring para construir aplicaciones **reactivas** (asíncronas, no bloqueantes).
- Usa **Mono** (para un solo valor) y **Flux** (para múltiples valores).
- Perfecto para alto rendimiento y concurrencia.

---

## 🍃 ¿Qué es MongoDB?

- MongoDB es una base de datos **NoSQL**, que guarda documentos tipo JSON (BSON).
- Ideal para aplicaciones modernas y flexibles.

---

## 🔄 ¿Qué es programación reactiva con MongoDB?

- Mongo tradicional es **bloqueante**.
- Mongo **reactivo** permite operaciones no bloqueantes con Spring WebFlux usando `spring-boot-starter-data-mongodb-reactive`.

---

## ✅ PASO A PASO

### 1. 🔧 Agregar la dependencia

**Maven:**

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>
```

**Gradle:**

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
```

---

### 2. 🛠 Configuración en `application.properties`

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/mydatabase
```

---

### 3. 📦 Crear el documento (entidad)

```java
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    // Getters y setters
}
```

---

### 4. 🗂 Repositorio reactivo

```java
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
```

---

### 5. 🧠 Servicio con Mono y Flux

```java
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Mono<Product> saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
```

---

## 🕹 ¿Qué son Mono y Flux?

| Tipo | Qué representa                 | Ejemplo                         |
|------|--------------------------------|----------------------------------|
| `Mono<T>` | 0 o 1 resultado (como un Optional) | Un producto, una respuesta, un error |
| `Flux<T>` | Muchos resultados (como una lista) | Lista de productos, historial, etc. |

---

## 🧪 Controlador REST

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public Flux<Product> findAll() {
        return productService.getAllProducts();
    }
}
```

---

## 🎯 ¿Por qué usar esto?

- Manejo eficiente de concurrencia.
- MongoDB es flexible.
- WebFlux escala fácilmente.
- `Mono` y `Flux` permiten procesamiento eficiente de datos.

---