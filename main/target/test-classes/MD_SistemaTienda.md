#  Supermarker - Sistema de Gestión de Tienda

Supermarker es una aplicación web robusta diseñada para la administración eficiente de inventarios, ventas y usuarios. Desarrollada con **Java** y **Spring Boot**, ofrece una interfaz intuitiva para el control total de una tienda minorista.

## Características Principales

* **Gestión de Productos:** CRUD completo con visualización dinámica de cartas en el frontend.
* **Control de Ventas:** Registro detallado de transacciones vinculadas a clientes y usuarios.
* **Administración de Usuarios:** Sistema de roles, perfiles personalizables y carga de imágenes.
* **Seguridad:** Manejo de sesiones de usuario y estados (Activo/Inactivo).
* **Interfaz Moderna:** Diseño responsivo utilizando Thymeleaf, CSS3 y Bootstrap Icons.

## 🛠Tecnologías Utilizadas

* **Backend:** Java 21, Spring Framework, Spring Data JPA.
* **Base de Datos:** MySQL
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript.
* **Herramientas:** Maven, Spring Boot

## Explicación de la Estructura del Proyecto

Basado en tu repositorio y el código que hemos trabajado, tu proyecto sigue una estructura Multicapa, que es el estándar de la industria. Aquí te explico qué hace cada parte:

### A. Backend (Capa Java)
1.  **Entity (Modelo):** Son las clases que representan las tablas de tu base de datos  Usan anotaciones para decirle a Java cómo mapear los datos.
2.  **Repository:** Es la capa de acceso a datos. Heredan de JpaRepository, lo que te permite hacer consultas a la DB sin escribir SQL manualmente.
3.  **Service:** Aquí reside la **lógica de negocio**. Es el intermediario entre el controlador y el repositorio. Aquí es donde podrías validar.
4.  **Controller (`ViewController.java`):** Es el "director de orquesta". Recibe las peticiones del navegador, llama a los servicios y decide qué página HTML mostrar al usuario.

### B. Frontend (Recursos)
1.  **Templates (Thymeleaf):** A diferencia del HTML estático, estos archivos permiten usar lógica para mostrar datos que vienen del controlador en tiempo real.
2.  **Static:** Aquí guardas tus estilos y logos. Estos archivos se cargan directamente en el navegador sin procesamiento del servidor.

### ¿Por qué esta estructura es buena?
Esta separación (MVC) permite que, si en el futuro quieres cambiar tu base de datos de MySQL a PostgreSQL, solo tengas que tocar la capa de configuración, sin romper tus archivos HTML o tu lógica de controladores.

### **Autor**

Jerry Lazaro Daniel Castro Rojas

