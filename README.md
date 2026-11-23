# TP-Programacion-III
# Sistema de Gestión de Naves Espaciales

**Trabajo Práctico Grupal – Programación III**  

## Descripción

Aplicación desarrollada en Java que simula la gestión y operación de naves espaciales, integrando conceptos de programación orientada a objetos, patrones de diseño, concurrencia, interfaces gráficas y persistencia de datos.

El sistema permite crear y administrar naves y tripulaciones, ejecutar misiones, consultar recursos e informes y utilizar un simulador concurrente de producción y consumo de energía.

## Tecnologías utilizadas

- Java
- Java Swing
- Maven
- Persistencia mediante archivos XML
- Programación concurrente con `Thread`, `synchronized`, `wait()` y `notifyAll()`

## Patrones de diseño implementados

| Patrón | Aplicación |
|---|---|
| Factory Method | Creación de diferentes tipos de naves |
| State | Administración de los estados del motor Warp |
| Template Method | Estructura común para la ejecución de misiones |
| Decorator | Cálculo de haberes de los tripulantes |
| Observer | Notificación de cambios y eventos del sistema |
| MVC | Separación entre modelo, vista y controlador |
| DAO | Acceso a los datos de usuarios |
| DTO | Transferencia de datos de autenticación |
| Productor-Consumidor | Simulación concurrente del Visor Recóndito |

## Estructura del proyecto

El código fuente se encuentra en `src/main/java` y está organizado en los siguientes paquetes:

- `naves`: modelos de naves y fábrica de creación.
- `motor`: estados y funcionamiento del motor Warp.
- `tripulacion`: administración de tripulantes y cálculo de haberes.
- `misiones`: implementación de misiones e informes.
- `asistente`: operaciones y gestión de la nave.
- `destinoytablero`: tablero espacial, posiciones y navegación.
- `elementos`: elementos presentes en el universo.
- `universoParalelo`: simulación concurrente productor-consumidor.
- `vista`: interfaces gráficas.
- `controlador`: controladores de las interfaces.
- `sistemaLogin`: autenticación y persistencia de usuarios.
- `bitacora`: registro de operaciones.

## Ejecución del proyecto

### Requisitos

- JDK compatible con la configuración del proyecto.
- Maven.
- Un entorno de desarrollo Java, como IntelliJ IDEA, Eclipse o VS Code.

### Pasos

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/simonfransanchez/TP-Programacion-III.git
   ```

2. Abrir el proyecto en el entorno de desarrollo y cargar sus dependencias Maven.

3. Ejecutar la clase `mainPruebaVistaLogin`, ubicada en `src/main/java`.

4. Desde la pantalla inicial, registrar un usuario o iniciar sesión con uno existente.

5. Crear una nave, configurar su tripulación y acceder a la ventana principal.

## Funcionalidades principales

### Gestión de naves y tripulantes

Permite crear distintos tipos de naves, administrar sus recursos, gestionar tripulantes y calcular sus haberes.

### Misiones espaciales

Implementa tres misiones mediante una estructura común basada en Template Method, incorporando navegación por tablero, registro de acontecimientos y generación de informes.

### Visor Recóndito

Simula la producción y el consumo concurrente de baterías mediante múltiples hilos.

Utiliza una plataforma compartida sincronizada y permite crear generadoras, incorporar naves consumidoras y detener la simulación.

Los acontecimientos se muestran en una consola gráfica mediante Observer.

### Autenticación y persistencia

Permite registrar usuarios e iniciar sesión. Las credenciales se almacenan en el archivo `datos/usuarios.xml` mediante una implementación DAO y objetos DTO.

## Documentación

El informe técnico del trabajo práctico contiene la descripción de la arquitectura, las decisiones de implementación, ejemplos de código y diagramas UML correspondientes a las entregas realizadas.

## Repositorio

https://github.com/simonfransanchez/TP-Programacion-III