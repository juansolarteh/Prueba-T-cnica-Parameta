# Prueba Técnica Parameta
Prueba Técnica para proceso de ingreso en Parameta.

**Desarrollado por:** Juan Pablo Solarte

## Descripción
Esta prueba técnica consiste en el desarrollo de dos servicios:

1. Un servicio `REST` que recibe información de un empleado, realiza validaciones de lógica de negocio y consume un servicio externo para almacenar dicha información.
2. Un servicio `SOAP` que recibe la información del empleado y la persiste en una base de datos `MySQL`.

### Decisiones arquitectónicas
Dado que el servicio REST es el responsable de recibir la información del empleado y validar la lógica de negocio, se optó por implementar una arquitectura 
limpia (Clean Architecture) inspirada en estándares de arquitectura hexagonal. La estructura del proyecto es la siguiente:

~~~
src/main/java/parameta/prueba/tecnica/servicioRest
├── application
│   ├── ports
│   │   ├── in
│   │   └── out
│   └── CreateEmployeeServiceImpl.java
├── domain
│   ├── entity
│   ├── exceptions
│   └── constants
├── infrastructure
│   ├── exceptionHandler
│   ├── httpController
│   ├── soapClient
└── PruebaTecnicaServicioRestApplication.java
~~~

- El paquete **domain** contiene las entidades de dominio, excepciones específicas y constantes necesarias para la definición y validación del agregado `Employee`.
- El paquete **application** define las interfaces (puertos) y la implementación del servicio que recibe la información del empleado y coordina la 
llamada al servicio SOAP externo (interfaz).
- El paquete **infrastructure** gestiona la comunicación HTTP, el manejo global de excepciones y la implementación del cliente SOAP para la persistencia.

Por otro lado, el servicio SOAP se implementó con una estructura más sencilla, dado que su única responsabilidad es recibir la información del empleado y almacenarla:

~~~
src/main/java/parameta/prueba/tecnica/servicioSoap
├── configurations
├── databaseModels
├── endpoints
├── mappers
├── repository
├── services
└── PruebaTecnicaServicioSoapApplication.java
~~~


Adicionalmente, se creó un módulo compartido que contiene los DTOs JAXB necesarios para la comunicación entre el servicio REST y el servicio SOAP.

## Instrucciones de ejecución

A continuación se describen dos formas de ejecutar los servicios: mediante `docker-compose` 
o ejecutando directamente los proyectos.

---

### Ejecución mediante docker-compose

#### Requisitos previos
- Docker instalado y en ejecución

#### Pasos para la ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/juansolarteh/Prueba-T-cnica-Parameta.git
   cd Prueba-T-cnica-Parameta
    ```
2. Construir las imágenes Docker:
    ```bash
    docker-compose build
    ```
3. Levantar los contenedores:
    ```bash
    docker-compose up
    ```
Esto compilará y ejecutará ambos servicios junto con una instancia de MySQL configurada con los valores por defecto.

**Nota:** La configuración por defecto no permite usar directamente el servicio SOAP, directamente el
servicio REST se encarga de consumirlo. Si desea probar el servicio SOAP de forma independiente, debe
descomentar la sección de puertos en el archivo `docker-compose.yml` para exponer el puerto 8081.

---

### Ejecución directa de proyecto

#### Requisitos previos
- Java 17 o superior
- Maven 3.8.6
- MySQL 8.0.32
- Docker (opcional, si se desea usar contenedor para MySQL)

#### Pasos para la ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/juansolarteh/Prueba-T-cnica-Parameta.git
   cd Prueba-T-cnica-Parameta
    ```
2. Configurar la base de datos MySQL con alguna de las siguientes opciones:
   1. Tener una base de datos MySQL corriendo localmente o en un contenedor Docker con la configuración
   definida en `application.properties` del servicio SOAP:
      - spring.datasource.url
      - spring.datasource.username
      - spring.datasource.password
      - Nombre de la base de datos incluida en la URL (por defecto `parameta123`)
      
   2. Alternativamente, definir las variables de entorno correspondientes:
      - SPRING_DATASOURCE_URL
      - SPRING_DATASOURCE_USERNAME
      - SPRING_DATASOURCE_PASSWORD
   3. Si tiene Docker, puede correr un contenedor MySQL con el siguiente comando desde la raíz del proyecto:
      ```bash
      docker-compose up mysql
      ```
      Esto levantará un contenedor MySQL con la configuración predeterminada del archivo `docker-compose.yml`.
3. Construir el módulo compartido, seguido de los proyectos con Maven:
   ```bash
    mvn clean install
   ```
4. Ejecutar cada uno de los servicios (SOAP y REST) mediante el comando:
   ```bash
   mvn spring-boot:run
   ```
   Debe ejecutar este comando en dos terminales diferentes, una para cada servicio:
   
Listo! Ahora ambos servicios están corriendo y puedes interactuar con ellos.