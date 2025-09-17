TaskManager Backend

Backend del proyecto de gestión de tareas desarrollado con Java + Spring Boot

Tabla de contenidos

Descripción

Tecnologías

Características

Estructura del proyecto

Instalación y puesta en marcha

Configuración

Endpoints de la API

Cómo contribuir

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Descripción

Este proyecto constituye la parte de backend de un sistema de gestión de tareas ("Task Manager"). Permite crear, actualizar, borrar y listar tareas; gestionar usuarios; controlar el estado de las tareas; etc.

Provee una API REST desarrollada con Spring Boot, la cual puede ser consumida por un cliente frontend, aplicaciones móviles, u otros servicios.

Tecnologías

Java

Spring Boot

Maven

MongoDB

Herramientas de construcción: Maven Wrapper (mvnw)

Características

Crear, editar, eliminar tareas

Listar tareas (posible filtrado por estado, usuario, etc.)

Gestión de usuarios (registro, autenticación, autorización)

Persistencia de datos

Buenas prácticas de estructura de proyecto

Estructura del proyecto

.
├── src/
│   ├── main/
│   │   ├── java/             ← Código fuente Java
│   │   └── resources/        ← Configuraciones, propiedades, etc.
├── .mvn/                    ← Wrapper de Maven
├── mvnw / mvnw.cmd           ← Scripts para ejecutar Maven sin tener que instalarlo globalmente
├── pom.xml                   ← POM de Maven con dependencias y configuración
├── .gitignore
└── README.md

Instalación y puesta en marcha

Pasos sugeridos para levantar el proyecto localmente:

Clona el repositorio

git clone https://github.com/JaviVL89/taskmanager-backend.git
cd taskmanager-backend

Verifica que la API esté corriendo (por defecto en http://localhost:8080, salvo que esté configurado otro puerto).

Configuración

Incluye los ajustes que podrías necesitar:

Puerto: modifica server.port en el archivo de configuración si quieres otro puerto.

Conexión a base de datos: URL, usuario, contraseña, driver, dialecto.

Perfil de Spring: dev, prod, etc.

CORS, seguridad, autenticación si aplica.

Endpoints de la API

Aquí algunos ejemplos de endpoints que podrías tener

Método	Ruta	Descripción
GET	/tasks	Obtener todas las tareas
GET	/tasks/{id}	Obtener tarea por ID
POST	/tasks	Crear una nueva tarea
PUT	/tasks/{id}	Actualizar una tarea existente
DELETE	/tasks/{id}	Eliminar una tarea
POST	/auth/login	Autenticación (login)
POST	/auth/register	Registro de usuario

Cómo contribuir

Si alguien quiere colaborar:

Haz un fork del repositorio

Crea una rama nueva para tu feature o bug (feature/nombre, bugfix/nombre)

Escribe código limpio, tests si es posible

Haz un pull request describiendo los cambios
