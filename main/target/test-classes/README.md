# **Sistema Tienda - Backend**

Backend desarrollado con Spring Boot para la gestión de una tienda, implementando una API REST con autenticación, autorización por roles y operaciones CRUD sobre las entidades principales del sistema.

### **Tecnologías utilizadas**
* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* Maven
* MySQL
* Arquitectura del proyecto

El proyecto sigue una arquitectura en capas típica de Spring:

Controller (@RestController)
Maneja las solicitudes HTTP y expone los endpoints de la API.
Service (@Service)
Contiene la lógica de negocio.
Repository (@Repository)
Gestiona el acceso a la base de datos mediante JPA.
Entity (@Entity)
Se encuentran las entidades de la base de datos con sus atributos.

Esta separación permite mantener el código organizado, escalable y fácil de mantener.

Conceptos clave implementados
API REST
CRUD
Autenticación y autorización
Arquitectura en capas
Persistencia con JPA 

#### **Autor**

Jerry Castro

#### **Notas**

Este proyecto es ideal como ejemplo para aprender:
Desarrollo backend con Spring Boot
Seguridad en APIs
Diseño de sistemas de ventas