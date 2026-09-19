# libros-api

API REST para administrar libros de una biblioteca (Laboratorio VI - Ejercicio 4).

## Ejecutar
```
mvn spring-boot:run
```
Puerto por defecto: 8081 (base: /api/libros)

## Endpoints
- POST   /api/libros
- GET    /api/libros            (soporta ?estado=)
- GET    /api/libros/titulo/{titulo}
- PUT    /api/libros/{id}
- DELETE /api/libros/{id}
