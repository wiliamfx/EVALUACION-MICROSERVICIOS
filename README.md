# 🛒 Sistema de Microservicios -GESTION DE VENTAS

Este proyecto implementa una **arquitectura de microservicios** con **Spring Boot + Spring Cloud Netflix**, centralizando el registro con **Eureka Server** y gestionando el tráfico a través de un **API Gateway**.  

Cada servicio está desacoplado y representa un dominio del negocio, permitiendo **escalabilidad**, **mantenibilidad** y **despliegue independiente**.  

---

## 📚 Arquitectura

- **Eureka Server** → Registro centralizado de microservicios (Service Discovery).  
- **API Gateway** → Punto de entrada único que enruta las peticiones hacia el microservicio correspondiente.  
- **Microservicios** → Cada dominio del negocio está desacoplado y desplegado de forma independiente.  

---

## 🚀 Microservicios y puertos

| Servicio        | Puerto | Endpoint Local                | Descripción                              |
|-----------------|--------|-------------------------------|------------------------------------------|
| **API-GATEWAY** | 8099   | http://localhost:8099         | Punto de entrada a todos los servicios. |
| **CLIENTES**    | 8094   | http://localhost:8094         | Gestión de clientes.                    |
| **DETALLEVENTA**| 8095   | http://localhost:8095         | Manejo de detalles de ventas.           |
| **PRODUCTOS**   | 8093   | http://localhost:8093         | Catálogo y gestión de productos.        |
| **PROVEEDORES** | 8092   | http://localhost:8092         | Gestión de proveedores.                 |
| **ROLES**       | 8091   | http://localhost:8091         | Manejo de roles de usuario.             |
| **USUARIOS**    | 8090   | http://localhost:8090         | Autenticación y gestión de usuarios.    |
| **VENTAS**      | 8096   | http://localhost:8096         | Registro y control de ventas.           |

> ⚠️ **Nota:** Los nombres de servicio en Eureka deben coincidir con `spring.application.name`  para enrutar correctamente mediante `lb://NOMBRE`.

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Cloud Netflix (Eureka Client, Eureka Server, Spring Cloud Gateway)**
- **Spring Data JPA / Hibernate**
- **MySQL 8+** o **PostgreSQL 13+**
- **Maven**
- **Lombok**
- **Docker** (opcional, para despliegue de BD o servicios)
- **Swagger/OpenAPI** *(opcional para documentar endpoints)*

---

## ⚙️ Configuración

### Variables de entorno

Crea un archivo **`.env`** en la raíz del proyecto:

```bash
# ========================
# Configuración de la BD en caso si quieres desplegar bueno seria dockerizarlo y conectar - utilizo mas railways para despliegue
# pero tmb es mas accesible para hacer pruebas de produccion.
# ========================
DB_HOST=localhost
DB_PORT=3306
DB_NAME=gestion
DB_USER=root
DB_PASS=123456

# ========================
# Eureka Server
# ========================
EUREKA_HOST=localhost
EUREKA_PORT=8761

# ========================
# Spring
# ========================
SPRING_PROFILES_ACTIVE=dev
