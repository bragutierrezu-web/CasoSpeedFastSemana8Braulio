# SpeedFast - Sistema de Gestión de Entregas

Proyecto desarrollado en Java para la gestión de pedidos y repartidores de la empresa SpeedFast.

La aplicación utiliza una interfaz gráfica desarrollada con Swing y una base de datos MySQL para almacenar la información de forma persistente.

## Funcionalidades

- Registrar nuevos pedidos.
- Registrar repartidores.
- Consultar y listar los pedidos registrados.
- Almacenar la información en una base de datos MySQL.
- Conexión entre Java y MySQL mediante JDBC.
- Validación de datos ingresados desde la interfaz gráfica.

## Tecnologías utilizadas

- Java
- Java Swing
- MySQL
- JDBC
- Maven
- IntelliJ IDEA
- MySQL Workbench

## Base de datos

El proyecto utiliza la base de datos:

speedfast_db

La base contiene las siguientes tablas:

- pedido
- repartidor
- entrega

La conexión se realiza de forma local mediante MySQL y JDBC.

## Estructura del proyecto

El proyecto se encuentra organizado principalmente en los siguientes paquetes:

- modelo: contiene las clases que representan las entidades del sistema.
- dao: contiene las clases encargadas de realizar operaciones con la base de datos.
- vista: contiene las ventanas e interfaces gráficas desarrolladas con Swing.
- controlador: contiene el punto de inicio y control de la aplicación.

## Operaciones con la base de datos

La aplicación utiliza JDBC para comunicarse con MySQL.

Actualmente permite registrar y consultar información de pedidos y repartidores utilizando clases DAO y sentencias SQL preparadas.

## Ejecución

Para utilizar el proyecto se requiere:

1. Tener MySQL Server en ejecución.
2. Crear la base de datos speedfast_db.
3. Ejecutar el script SQL correspondiente.
4. Configurar las credenciales locales de conexión.
5. Ejecutar la clase Main desde IntelliJ IDEA.

## Configuración de conexión

La configuración de la base de datos se almacena de forma separada mediante un archivo de propiedades con los datos necesarios para establecer la conexión JDBC.

Por seguridad, las credenciales utilizadas para acceder a MySQL deben corresponder a la configuración local de cada usuario.

## Proyecto académico

Proyecto desarrollado como parte de las actividades de Programación Orientada a Objetos, aplicando conceptos de:

- Programación Orientada a Objetos.
- Arquitectura por capas.
- Interfaces gráficas con Swing.
- Persistencia de datos.
- JDBC.
- MySQL.
- Patrón DAO.
Autor: Braulio Gutiérrez.