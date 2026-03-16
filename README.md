# Datos API

API REST sencilla en Spring Boot para consultar y anadir datos en memoria.

## Tecnologias

- Java 21
- Spring Boot 4.0.3
- Maven Wrapper

## Requisitos

- JDK 21

## Ejecutar el proyecto

Desde la raiz del proyecto:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

La API queda disponible en:

- Base URL: `http://localhost:8080/api`

## Modelo de dato

Cada elemento tiene este formato:

```json
{
  "codigo": 1,
  "descripcion": "Primer elemento de prueba"
}
```

## Endpoints

### 1) Obtener todos los datos

- Metodo: `GET`
- URL: `/api/datos`

Ejemplo con curl:

```bash
curl "http://localhost:8080/api/datos"
```

### 2) Obtener datos por codigo

- Metodo: `GET`
- URL: `/api/datos?codigo={codigo}`

Ejemplo con curl:

```bash
curl "http://localhost:8080/api/datos?codigo=1"
```

### 3) Obtener datos por descripcion

- Metodo: `GET`
- URL: `/api/datos?descripcion={texto}`

Ejemplo con curl:

```bash
curl "http://localhost:8080/api/datos?descripcion=Primer"
```

### 4) Anadir un dato

- Metodo: `POST`
- URL: `/api/datos?codigo={codigo}&descripcion={descripcion}`
- Cuerpo: no requerido (se envian parametros por query string)

Ejemplo con curl:

```bash
curl -X POST "http://localhost:8080/api/datos?codigo=6&descripcion=Elemento%20nuevo"
```

Comprobar que se anadio:

```bash
curl "http://localhost:8080/api/datos?codigo=6"
```

## Notas

- Los datos se guardan en memoria (lista en `DatosDAO`).
- Al reiniciar la aplicacion, se pierden los datos anadidos durante la ejecucion.
